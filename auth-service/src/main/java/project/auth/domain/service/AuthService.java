package project.auth.domain.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import project.auth.application.presenter.request.LoginRequest;
import project.auth.application.presenter.response.LoginResponse;
import project.auth.application.presenter.response.TokenResponse;
import project.auth.domain.entity.Account;
import project.core.application.enums.MailFormat;
import project.core.application.exceptions.BadRequestException;
import project.core.application.model.response.BaseResponse;
import project.core.infras.security.JwtProvider;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

	private final AccountService accountService;

	private final JwtProvider jwtProvider;

	private final AuthenticationManager authenticationManager;

	public BaseResponse<LoginResponse> login(LoginRequest request) throws BadRequestException {
		Account account;
		if (!isMail(request.getIdentifier())) {
			account = accountService.findByFields(Map.of("username", request.getIdentifier()));
		} else {
			account = accountService.findByFields(Map.of("email", request.getIdentifier()));
		}

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(account.getUsername(), request.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			final String accessToken = jwtProvider.generateToken(account.getUsername());
			final String refreshToken = UUID.randomUUID().toString();

			return new BaseResponse<>(true, "Success!", LoginResponse.builder()
					.id(account.getId())
					.username(account.getUsername())
					.email(account.getEmail())
					.token(TokenResponse.builder()
							.accessToken(accessToken)
							.refreshToken(refreshToken)
							.build())
					.build());

		} catch (Exception e) {
			log.error("{} - {}", e.getClass().getSimpleName(), e.getMessage());
			throw new BadRequestException("Invalid credential!");
		}
	}

	private boolean isMail(String identifier) {
		return identifier.contains(MailFormat.VTI_MAIL.getValue())
				|| identifier.contains(MailFormat.FPT_EDU_MAIL.getValue())
				|| identifier.contains(MailFormat.FPT_MAIL.getValue())
				|| identifier.contains(MailFormat.STARDARD_MAIL.getValue());
	}
}
