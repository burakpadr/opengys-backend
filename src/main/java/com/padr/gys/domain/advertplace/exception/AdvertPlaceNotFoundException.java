package com.padr.gys.domain.advertplace.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class AdvertPlaceNotFoundException extends BaseException {
    
    private static final String CODE = "ADVERT_PLACE_NOT_FOUND_EXCEPTION";

    public AdvertPlaceNotFoundException(Long id) {
        super(CODE, String.format("%d id'li ilan yeri kaydı bulunamadı!", id));
    }
}
