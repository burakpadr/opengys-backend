package com.padr.gys.domain.payment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentDeclarationApprovementStatus {
    
    WAITING("Bekliyor"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi");

    private final String value;
}
