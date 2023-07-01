package com.padr.gys.infra.inbound.common.advice;

import com.padr.gys.domain.realestate.common.exception.BaseException;
import com.padr.gys.domain.realestate.common.model.response.ExceptionResponse;

public class BaseAdvice {
    
    protected ExceptionResponse generateExceptionResponse(final BaseException exception) {
        return ExceptionResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
    }
}