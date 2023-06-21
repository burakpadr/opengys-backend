package com.abctech.gys.domain.realestate.status.exception;

import com.abctech.gys.domain.realestate.common.exception.BaseException;

public class StatusNotFoundException extends BaseException {
    
    private static final String CODE = "STATUS_NOT_FOUND_EXCEPTION";

    public StatusNotFoundException(String message) {
        super(CODE, String.format("'%s' isimli bir statü bulunamadı!", message));
    }
}
