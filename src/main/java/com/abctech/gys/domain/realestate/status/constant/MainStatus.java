package com.abctech.gys.domain.realestate.status.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainStatus {
    
    FOR_RENT("Kiralık"),
    FOR_SALE("Satılık");

    private String value;
}
