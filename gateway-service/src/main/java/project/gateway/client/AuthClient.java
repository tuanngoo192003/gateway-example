package project.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import project.core.domain.service.GrpcClientBaseService;
import project.core.grpc.auth.AuthFilterServiceGrpc;
import project.core.grpc.auth.HasPermissionProto.HasPermisisonResponse;
import project.core.grpc.auth.HasPermissionProto.HasPermissionRequest;

@Service
public class AuthClient extends GrpcClientBaseService {

    @Value("${grpc.auth.host}")
    private String authHost;

    @Value("${grpc.auth.port}")
    private Integer authPort;

    public Boolean hasPermission(String token, String uri, String method) {
        HasPermisisonResponse response = execute(channel -> {
            AuthFilterServiceGrpc.AuthFilterServiceBlockingStub stub = AuthFilterServiceGrpc.newBlockingStub(channel);

            HasPermissionRequest request = HasPermissionRequest.newBuilder()
                    .setToken(token)
                    .setUri(uri)
                    .setMethod(method)
                    .build();

            return stub.hasPermission(request);
        }, authHost, authPort);

        return response.getAllowed();
    }
}
