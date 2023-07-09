package com.padr.gys.infra.inbound.advert.advice;

import com.padr.gys.domain.advert.exception.AdvertNotFoundException;
import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class AdvertAdvice extends BaseAdvice {

    @ExceptionHandler(AdvertNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(AdvertNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }
}
