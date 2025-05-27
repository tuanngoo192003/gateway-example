package project.auth.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.auth.application.payload.request.PermissionRequest;
import project.auth.application.payload.response.PermissionResponse;
import project.core.application.BaseController;
import project.core.application.model.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @GetMapping("")
	public ResponseEntity<BaseResponse<PermissionResponse>> getPermission() throws Exception {
        // validateRequest(permissionRequest);
		return doResponse(PermissionResponse.builder().allow(true).build());
	}
}
