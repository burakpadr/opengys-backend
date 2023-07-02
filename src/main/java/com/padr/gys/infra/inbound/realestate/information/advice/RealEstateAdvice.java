package com.padr.gys.infra.inbound.realestate.information.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.domain.realestate.information.exception.RealEstateAlreadyExistException;
import com.padr.gys.domain.realestate.information.exception.RealEstateNotFoundException;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class RealEstateAdvice extends BaseAdvice {

    @ExceptionHandler(RealEstateNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(RealEstateNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }

    @ExceptionHandler(RealEstateAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleException(RealEstateAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(generateExceptionResponse(exception));
    }
}
