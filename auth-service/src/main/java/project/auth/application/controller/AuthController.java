package project.auth.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.auth.application.payload.request.LoginRequest;
import project.auth.application.payload.response.LoginResponse;
import project.core.application.BaseController;
import project.core.application.model.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @PostMapping("/login")
	public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) throws Exception {
        validateRequest(loginRequest);
		return doResponse(null);
	}
}
