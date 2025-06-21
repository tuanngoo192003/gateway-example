package project.auth.infras.security;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import project.auth.domain.entity.Account;
import project.auth.domain.service.AccountService;
import project.core.infras.security.Permission;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountService.findByFields(Map.of("username", username));
            if (account != null) {
                List<SimpleGrantedAuthority> authorization = account.getRole().getPermissions()
                        .stream().map(permission -> {
                            try {
                                Permission reconstructedPermission = Permission.builder()
                                        .uri(permission.getUri())
                                        .method(permission.getMethod())
                                        .build();
                                return new SimpleGrantedAuthority(
                                        objectMapper.writeValueAsString(reconstructedPermission));
                            } catch (JsonProcessingException jpe) {
                                throw new RuntimeException("Failed to convert permission to string", jpe);
                            }
                        })
                        .toList();
                return User.withUsername(username)
                        .password(account.getPassword())
                        .authorities(authorization)
                        .build();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
