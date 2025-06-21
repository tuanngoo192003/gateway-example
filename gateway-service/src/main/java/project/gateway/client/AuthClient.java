package project.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthClient {

    @Value("${custom.auth.get-permission-uri}")
    private String getPermissionUri;
    
    public Boolean hasPermission(String token, String uri, String method) {
        return true;
    }
}
