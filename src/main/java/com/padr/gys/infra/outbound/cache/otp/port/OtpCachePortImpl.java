package com.padr.gys.infra.outbound.cache.otp.port;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;
import com.padr.gys.infra.outbound.cache.otp.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class OtpCachePortImpl implements OtpCachePort {

    private final OtpRepository otpRepository;

    @Override
    public Optional<Otp> findByOtpTypeAndKey(OtpType otpType, String key) {
        return otpRepository.findByOtpTypeAndKey(otpType, key);
    }

    @Override
    public void delete(Otp otp) {
        otpRepository.delete(otp);
    }

    @Override
    public Otp save(Otp otp) {
        return otpRepository.save(otp);
    }
}
