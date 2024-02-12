package com.padr.gys.domain.otp.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OtpType {
    
    PASSWORD_RESET(3);

    private final Integer maxNumberOfAttemptsAllowed;
}
