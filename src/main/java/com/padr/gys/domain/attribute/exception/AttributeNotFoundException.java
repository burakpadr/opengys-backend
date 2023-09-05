package com.padr.gys.domain.attribute.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class AttributeNotFoundException extends BaseException {
    
    private static final String CODE = "ATTRIBUTE_NOT_FOUND_EXCEPTION";
    private static final String MESSAGE = "Özellik bulunamadı!";

    public AttributeNotFoundException() {
        super(CODE, MESSAGE);
    }
}
