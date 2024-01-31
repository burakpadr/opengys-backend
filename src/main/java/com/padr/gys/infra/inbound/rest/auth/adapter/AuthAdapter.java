package com.padr.gys.infra.inbound.rest.auth.adapter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.auth.model.request.AuthRequest;
import com.padr.gys.infra.inbound.rest.auth.usecase.CreateJwtTokenUseCase;

@RestController
@RequestMapping("/gys/api/v1/auth")
@RequiredArgsConstructor
public class AuthAdapter {

    private final CreateJwtTokenUseCase createJwtTokenUseCase;

    @PostMapping
    public void createToken(HttpServletResponse response, @Valid @RequestBody AuthRequest request) {
        createJwtTokenUseCase.execute(response, request);
    }
}
