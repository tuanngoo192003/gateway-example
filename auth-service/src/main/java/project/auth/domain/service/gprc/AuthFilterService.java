package project.auth.domain.service.gprc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import project.core.application.exceptions.BadRequestException;
import project.core.grpc.auth.HasPermissionProto;
import project.core.grpc.auth.AuthFilterServiceGrpc.AuthFilterServiceImplBase;
import project.core.infras.security.JwtProvider;
import project.core.infras.security.Permission;

@GrpcService
@Log4j2
@RequiredArgsConstructor
public class AuthFilterService extends AuthFilterServiceImplBase {

    private final JwtProvider jwtProvider;

    private final UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void hasPermission(HasPermissionProto.HasPermissionRequest request,
            StreamObserver<HasPermissionProto.HasPermisisonResponse> responseObserver) {
        HasPermissionProto.HasPermisisonResponse.Builder response = HasPermissionProto.HasPermisisonResponse
                .newBuilder();
        try {
            String username = jwtProvider.getUsernameFromToken(request.getToken());

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            List<Permission> permissions = buildPermissions(userDetails);

            Permission currentAccess = Permission.builder()
                    .uri(request.getUri())
                    .method(request.getMethod())
                    .build();

            if (!permissions.contains(currentAccess)) {
                log.info("permissions: {}", permissions);
                log.info("currentAccess: {}", currentAccess);
                response.setAllowed(false);
                responseObserver.onNext(response.build());
                responseObserver.onCompleted();
                return;
            }

            response.setAllowed(true);
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());

            response.setAllowed(false);
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }

    }

    private List<Permission> buildPermissions(UserDetails userDetails) throws BadRequestException {
        List<Permission> permissions = new ArrayList<>();
        try {
            userDetails.getAuthorities().stream().forEach(authority -> {
                try {
                    Permission permission = objectMapper.readValue(authority.getAuthority(),
                            Permission.class);
                    permissions.add(permission);
                } catch (JsonProcessingException jpe) {
                    throw new BadRequestException("Permission parsing failed!");
                }
            });
        } catch (BadRequestException e) {
            throw e;
        }
        return permissions;
    }
}
