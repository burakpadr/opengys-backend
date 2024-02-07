package com.padr.gys.infra.outbound.cache.otp.port;

import java.util.Optional;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;

public interface OtpCachePort {
    
    Optional<Otp> findByOtpTypeAndKey(OtpType otpType, String key);

    void delete(Otp otp);

    Otp save(Otp otp);
}
