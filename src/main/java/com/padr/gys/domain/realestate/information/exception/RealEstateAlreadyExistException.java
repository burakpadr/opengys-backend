package com.padr.gys.domain.realestate.information.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class RealEstateAlreadyExistException extends BaseException {

    private static final String CODE = "REAL_ESTATE_ALREADY_EXIST_EXCEPTION";

    public RealEstateAlreadyExistException(String no) {
        super(CODE, String.format("%s no'lu gayrimenkul zaten mevcut!", no));
    }
}
