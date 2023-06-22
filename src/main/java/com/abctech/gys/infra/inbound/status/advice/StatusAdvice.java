package com.abctech.gys.infra.inbound.status.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abctech.gys.domain.realestate.common.model.response.ExceptionResponse;
import com.abctech.gys.domain.realestate.status.exception.StatusNotFoundException;
import com.abctech.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.abctech.gys.infra.inbound.status.adapter")
public class StatusAdvice extends BaseAdvice {

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(StatusNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }
}