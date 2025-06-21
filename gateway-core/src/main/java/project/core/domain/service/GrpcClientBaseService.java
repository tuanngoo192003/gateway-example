package project.core.domain.service;

import java.util.function.Function;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GrpcClientBaseService {
    protected <T> T execute(Function<ManagedChannel, T> grpcCall, String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        try{
            return grpcCall.apply(channel);
        } catch (IllegalArgumentException e){
            log.error("--> Error executing grpc call",e);
            return null;
        } finally {
            channel.shutdown();
        }
    }
}
