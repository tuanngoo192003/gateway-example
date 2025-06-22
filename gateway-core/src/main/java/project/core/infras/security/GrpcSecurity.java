package project.core.infras.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;

@Configuration
public class GrpcSecurity {
    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        return (ServerCall<?, ?> call, Metadata headers) -> null;
    }
}
