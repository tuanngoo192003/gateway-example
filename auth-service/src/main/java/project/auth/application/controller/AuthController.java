package project.auth.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.auth.application.presenter.request.LoginRequest;
import project.auth.application.presenter.response.LoginResponse;
import project.auth.domain.service.AuthService;
import project.core.application.BaseController;
import project.core.application.model.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController extends BaseController {

	private final AuthService authService;

    @PostMapping("/login")
	public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) throws Exception {
        validateRequest(loginRequest);
		LoginResponse response = authService.login(loginRequest);
		return doResponse(response);
	}
}
