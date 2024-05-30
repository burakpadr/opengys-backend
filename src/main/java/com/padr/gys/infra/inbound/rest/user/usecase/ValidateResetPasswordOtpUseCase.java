package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.ValidateResetPasswordOtpRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.ValidateResetPasswordOtpResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ValidateResetPasswordOtpUseCase {

    private final OtpServicePort otpServicePort;

    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;

    public ValidateResetPasswordOtpResponse execute(ValidateResetPasswordOtpRequest request) {
        userPersistencePort.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        boolean isMatched = otpServicePort.validate(OtpType.PASSWORD_RESET, request.getEmail(),
                request.getOtp());

        return ValidateResetPasswordOtpResponse.builder()
                .isMatched(isMatched)
                .build();
    }
}
