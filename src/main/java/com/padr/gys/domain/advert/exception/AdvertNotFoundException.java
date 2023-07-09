package com.padr.gys.domain.advert.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class AdvertNotFoundException extends BaseException {
    
    private static final String CODE = "ADVERT_NOT_FOUND_EXCEPTION";

    public AdvertNotFoundException(Long id) {
        super(CODE, String.format("%d id'li ilan kaydı bulunamadı!", id));
    }
}
