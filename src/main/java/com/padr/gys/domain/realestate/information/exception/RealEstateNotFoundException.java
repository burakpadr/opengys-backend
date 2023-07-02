package com.padr.gys.domain.realestate.information.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class RealEstateNotFoundException extends BaseException {

    private static final String CODE = "REAL_ESTATE_NOT_FOUND_EXCEPTION";

    public RealEstateNotFoundException(Long id) {
        super(CODE, String.format("%d id'li gayrimenkul kaydı bulunamadı", id));
    }

    public RealEstateNotFoundException(String no) {
        super(CODE, String.format("%s no'lu gayrimenkul kaydı bulunamadı", no));
    }
}
