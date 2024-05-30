package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.rest.user.model.request.ResetUserPasswordOtpRequest;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ResetUserPasswordUseCase {
    
    private final OtpServicePort otpServicePort;

    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    public void execute(ResetUserPasswordOtpRequest request) {
        Otp otp = otpServicePort.findByOtpTypeAndKey(OtpType.PASSWORD_RESET, request.getEmail());

        if (otp.getIsValidated()) {
            User user = userPersistencePort.findByEmail(request.getEmail())
                    .orElseThrow(() -> new NoSuchElementException(
                            messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userPersistencePort.save(user);
        }
    }
}
