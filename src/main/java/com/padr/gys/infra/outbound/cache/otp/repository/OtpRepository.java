package com.padr.gys.infra.outbound.cache.otp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;

@Repository
public interface OtpRepository extends CrudRepository<Otp, String> {
    
    Optional<Otp> findByOtpTypeAndKey(OtpType otpType, String key);
}
