package com.padr.gys.domain.otp.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.infra.outbound.cache.otp.port.OtpCachePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class OtpService implements OtpServicePort {

    private final OtpCachePort otpCachePort;

    private final MessageSource messageSource;

    @Override
    public Otp create(OtpType otpType, String key) {
        Optional<Otp> duplicatedOtpOptional = otpCachePort.findByOtpTypeAndKey(otpType, key);

        if (duplicatedOtpOptional.isPresent())
            otpCachePort.delete(duplicatedOtpOptional.get());

        Otp otp = Otp.builder()
                .otpType(otpType)
                .key(key)
                .otp(String.format("%06d", new Random().nextInt(999999))) // 6 digit number
                .build();

        return otpCachePort.save(otp);
    }

    @Override
    public Otp findByOtpTypeAndKey(OtpType otpType, String key) {
        return otpCachePort.findByOtpTypeAndKey(otpType, key).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("otp.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public boolean validate(OtpType otpType, String key, String otpValueEnteredByUser) {
        Otp otp = findByOtpTypeAndKey(otpType, key);

        if (otpType.getMaxNumberOfAttemptsAllowed().equals(otp.getNumberOfFailures())) {
            otpCachePort.delete(otp);

            throw new BusinessException(messageSource.getMessage("otp.reached-max-number-of-attempts-allowed", null,
                    LocaleContextHolder.getLocale()));
        }

        if (otp.getOtp().equals(otpValueEnteredByUser)) {
            otpCachePort.delete(otp);

            return true;
        }

        otp.setNumberOfFailures(otp.getNumberOfFailures() + 1);

        otpCachePort.save(otp);

        return false;
    }
}
