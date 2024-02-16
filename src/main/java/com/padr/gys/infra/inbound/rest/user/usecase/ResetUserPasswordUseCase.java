package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.ResetUserPasswordOtpRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResetUserPasswordUseCase {
    
    private final UserServicePort userServicePort;
    private final OtpServicePort otpServicePort;

    public void execute(ResetUserPasswordOtpRequest request) {
        Otp otp = otpServicePort.findByOtpTypeAndKey(OtpType.PASSWORD_RESET, request.getEmail());

        if (otp.getIsValidated()) {
            User user = userServicePort.findByEmail(request.getEmail());

            userServicePort.changePassword(user, request.getPassword());
        }
    }
}
