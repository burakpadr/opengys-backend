package com.padr.gys.domain.user.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class UserAlreadyExistException extends BaseException {

    private static final String CODE = "USER_ALREADY_EXIST_EXCEPTION";
    private static final String MESSAGE = "Bu bilgilerle eşleştirilmiş zaten bir kullanıcı var!";

    public UserAlreadyExistException() {
        super(CODE, MESSAGE);
    }
}
