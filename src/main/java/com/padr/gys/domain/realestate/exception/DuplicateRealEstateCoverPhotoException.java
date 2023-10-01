package com.padr.gys.domain.realestate.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class DuplicateRealEstateCoverPhotoException extends BaseException {

    private static final String CODE = "DUPLICATE_REAL_ESTATE_COVER_PHOTO_EXCEPTION";
    private static final String MESSAGE = "Sadece bir tane kapak fotoğrafı seçilebilir!";

    public DuplicateRealEstateCoverPhotoException() {
        super(CODE, MESSAGE);
    }
}
