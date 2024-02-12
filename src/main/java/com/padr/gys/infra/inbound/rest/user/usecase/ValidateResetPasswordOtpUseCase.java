package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.user.model.request.ValidateResetPasswordOtpRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.ValidateResetPasswordOtpResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidateResetPasswordOtpUseCase {

    private final OtpServicePort otpServicePort;

    public ValidateResetPasswordOtpResponse execute(ValidateResetPasswordOtpRequest request) {
        boolean isMatched = otpServicePort.validate(OtpType.PASSWORD_RESET, UserContext.getUser().getEmail(),
                request.getOtp());

        return ValidateResetPasswordOtpResponse.builder()
                .isMatched(isMatched)
                .build();
    }
}
