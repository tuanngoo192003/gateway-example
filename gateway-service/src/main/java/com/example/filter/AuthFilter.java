package com.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.example.client.AuthClient;
import com.example.helper.ResponseHelper;
import com.example.helper.TokenHelper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthFilter implements GatewayFilterFactory<AuthFilter.Config>{

    private final AuthClient authClient;

    private final TokenHelper tokenHelper;

    private final ResponseHelper responseHelper;

    public static class Config {
        
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

	@Override
	public Config newConfig() {
        return new Config();
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = tokenHelper.extractToken(request);

            if (token == null || !tokenHelper.isValid(token)) {
                return responseHelper.unauthorized(exchange);
            }

            String path = request.getURI().getPath();
            String method = request.getMethod().name();

            // Call auth-service to validate permission
            boolean hasPermission = authClient.hasPermission(token, path, method);
            if (!hasPermission) {
                return responseHelper.forbidden(exchange);
            }

            return chain.filter(exchange);
        };
    }

}
