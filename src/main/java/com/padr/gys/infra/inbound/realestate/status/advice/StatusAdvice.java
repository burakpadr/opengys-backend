package com.padr.gys.infra.inbound.realestate.status.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.domain.realestate.status.exception.StatusNotFoundException;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class StatusAdvice extends BaseAdvice {

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(StatusNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }
}
