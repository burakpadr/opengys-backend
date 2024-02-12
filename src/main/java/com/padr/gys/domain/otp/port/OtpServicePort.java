package com.padr.gys.domain.otp.port;

import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;

public interface OtpServicePort {

    Otp create(OtpType otpType, String key);

    Otp findByOtpTypeAndKey(OtpType otpType, String key);

    boolean validate(OtpType otpType, String key, String otpValueEnteredByUser);
}
