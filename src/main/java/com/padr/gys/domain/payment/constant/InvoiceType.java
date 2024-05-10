package com.padr.gys.domain.payment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvoiceType {
    
    RENT_PAYMENT("Kira Faturası");

    private final String value;
}
