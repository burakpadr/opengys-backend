package com.padr.gys.domain.carrier.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {
    
    PASSWORD_RESET_OTP("reset-password", "OpenGYS Parola Yenileme Kodu"),
    STAFF_ACCOUNT_HAS_BEEN_CREATED("staff-account-has-been-created", "OpenGYS Hesabı");

    private final String templateName;
    private final String subject;
}
