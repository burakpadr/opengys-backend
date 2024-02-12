package com.padr.gys.infra.inbound.rest.user.adapter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.user.model.request.ValidateResetPasswordOtpRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.ValidateResetPasswordOtpResponse;
import com.padr.gys.infra.inbound.rest.user.usecase.CreateResetPasswordOtpUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.ValidateResetPasswordOtpUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/users")
@RequiredArgsConstructor
public class UserAdapter {

    private final CreateResetPasswordOtpUseCase createResetPasswordOtpUseCase;
    private final ValidateResetPasswordOtpUseCase validateResetPasswordOtpUseCase;

    @PostMapping("/create-reset-password-otp")
    public void createResetPasswordOtp() {
        createResetPasswordOtpUseCase.execute();
    }

    @PostMapping("/validate-reset-password-otp")
    public ValidateResetPasswordOtpResponse validateResetPasswordOtp(
            @Valid @RequestBody ValidateResetPasswordOtpRequest request) {
        
        return validateResetPasswordOtpUseCase.execute(request);
    }
}
