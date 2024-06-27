package com.padr.gys.domain.carrier.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {
    
    PASSWORD_RESET_OTP("reset-password", "OpenGYS Reset Password Code"),
    STAFF_ACCOUNT_HAS_BEEN_CREATED("staff-account-has-been-created", "OpenGYS Staff Account"),
    TENANT_ACCOUNT_HAS_BEEN_CREATED("tenant-account-has-been-created", "OpenGYS Tenant Account");

    private final String templateName;
    private final String subject;
}
