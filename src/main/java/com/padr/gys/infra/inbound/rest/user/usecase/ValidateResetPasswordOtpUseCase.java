package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.ValidateResetPasswordOtpRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.ValidateResetPasswordOtpResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidateResetPasswordOtpUseCase {

    private final OtpServicePort otpServicePort;
    private final UserServicePort userServicePort;

    public ValidateResetPasswordOtpResponse execute(ValidateResetPasswordOtpRequest request) {
        userServicePort.findByEmail(request.getEmail());

        boolean isMatched = otpServicePort.validate(OtpType.PASSWORD_RESET, request.getEmail(),
                request.getOtp());

        return ValidateResetPasswordOtpResponse.builder()
                .isMatched(isMatched)
                .build();
    }
}
