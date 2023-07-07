package com.padr.gys.infra.inbound.adverplace.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.advertplace.exception.AdvertPlaceNotFoundException;
import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class AdvertPlaceAdvice extends BaseAdvice {

    @ExceptionHandler(AdvertPlaceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(AdvertPlaceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }   
}
