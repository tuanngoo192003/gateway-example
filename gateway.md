```java
@Component
public class AuthFilter implements GatewayFilterFactory<AuthFilter.Config> {

    // inject AuthServiceClient here

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = extractToken(request);

            if (token == null || !isValid(token)) {
                return unauthorized(exchange);
            }

            String path = request.getURI().getPath();
            String method = request.getMethod().name();

            // Call auth-service to validate permission
            boolean hasPermission = authClient.hasPermission(token, path, method);
            if (!hasPermission) {
                return forbidden(exchange);
            }

            return chain.filter(exchange);
        };
    }

    // Helper methods...
}
```

```java
@Component
public class AuthServiceClient {

    private final WebClient webClient;

    public AuthServiceClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://auth-service").build(); // service name in Eureka
    }

    public Mono<Boolean> hasPermission(String token, String uri, String method) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/internal/check-permission")
                        .queryParam("uri", uri)
                        .queryParam("method", method)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
```

```java
@RestController
@RequestMapping("/internal")
public class PermissionController {

    @GetMapping("/check-permission")
    public ResponseEntity<Boolean> checkPermission(
        @RequestParam String uri,
        @RequestParam String method,
        @RequestHeader("Authorization") String token
    ) {
        // 1. Parse & validate token
        // 2. Get user role
        // 3. Compare URI & method with permission table
        boolean allowed = permissionService.isAllowed(token, uri, method);
        return ResponseEntity.ok(allowed);
    }
}
```
https://www.geeksforgeeks.org/spring-boot-eureka-server/
