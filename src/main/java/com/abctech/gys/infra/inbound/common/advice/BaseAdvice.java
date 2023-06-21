package com.abctech.gys.infra.inbound.common.advice;

import com.abctech.gys.infra.inbound.common.exception.BaseException;
import com.abctech.gys.infra.inbound.common.model.response.ExceptionResponse;

public class BaseAdvice {
    
    protected ExceptionResponse generateExceptionResponse(final BaseException exception) {
        return ExceptionResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
    }
}