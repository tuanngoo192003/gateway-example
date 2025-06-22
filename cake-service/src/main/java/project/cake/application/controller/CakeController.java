package project.cake.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.cake.application.presenter.request.CakeCreateRequest;
import project.cake.application.presenter.request.CakeUpdateRequest;
import project.cake.application.presenter.response.CakeCreateResponse;
import project.cake.application.presenter.response.CakeUpdateResponse;
import project.cake.application.presenter.response.GetCakeResponse;
import project.cake.domain.service.CakeService;
import project.core.application.BaseController;
import project.core.application.model.response.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cake")
public class CakeController extends BaseController {
    
    private final CakeService cakeService;

    @PostMapping("/")
	public ResponseEntity<BaseResponse<CakeCreateResponse>> createCake(@RequestBody CakeCreateRequest request) throws Exception {
        validateRequest(request);
        CakeCreateResponse response = cakeService.createCake(request);
        return doResponse(response);
	}

    @PutMapping("/")
	public ResponseEntity<BaseResponse<CakeUpdateResponse>> updateCake(@RequestBody CakeUpdateRequest request) throws Exception {
        validateRequest(request);
        CakeUpdateResponse response = cakeService.updateCake(request);
        return doResponse(response);
	}

    @GetMapping("/all")
	public ResponseEntity<BaseResponse<List<GetCakeResponse>>> getCake() throws Exception {
        return doResponse(cakeService.getCakes());
	}
}
