package com.padr.gys.infra.inbound.categorization.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.categorization.exception.CategoryNotFoundException;
import com.padr.gys.domain.categorization.exception.SubCategoryNotFoundException;
import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class CategorizationAdvice extends BaseAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }

    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(SubCategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }
}