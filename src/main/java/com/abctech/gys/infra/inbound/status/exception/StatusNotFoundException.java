package com.abctech.gys.infra.inbound.status.exception;

import com.abctech.gys.infra.inbound.common.exception.BaseException;

public class StatusNotFoundException extends BaseException {
    
    private static final String CODE = "STATUS_NOT_FOUND_EXCEPTION";

    public StatusNotFoundException(String message) {
        super(CODE, String.format("'%s' isimli bir statü bulunamadı!", message));
    }
}
