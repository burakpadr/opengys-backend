package com.padr.gys.domain.otp.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import com.padr.gys.domain.otp.constant.OtpType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("Otp")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

    private String id;

    @Indexed
    private OtpType otpType;

    @Indexed
    private String key;

    private String otp;

    @Builder.Default
    private Integer numberOfFailures = 0;

    @Builder.Default
    private Boolean isValidated = false;

    @TimeToLive(unit = TimeUnit.SECONDS)
    @Builder.Default
    private Long maxLifetime = 3600L; // 3600 seconds = 1 hour
}
