package com.padr.gys.domain.user.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class UserNotFoundException extends BaseException {

    private final static String CODE = "USER_NOT_FOUND_EXCEPTION";
    private final static String MESSAGE = "Kullanıcı kaydı bulunamadı!";

    public UserNotFoundException() {
        super(CODE, MESSAGE);
    }
}
