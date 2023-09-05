package com.padr.gys.infra.inbound.attribute.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.attribute.exception.AttributeNotFoundException;
import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class AttributeAdvice extends BaseAdvice {

    @ExceptionHandler(AttributeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(AttributeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }
}
