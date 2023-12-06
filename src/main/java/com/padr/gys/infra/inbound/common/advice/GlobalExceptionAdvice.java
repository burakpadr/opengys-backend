package com.padr.gys.infra.inbound.common.advice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.common.model.response.ExceptionResponse;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionAdvice {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
                String code = RuntimeException.class.getName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        MethodArgumentNotValidException exception) {
                
                String code =  MethodArgumentNotValidException.class.getName();

                String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getField)
                                .collect(Collectors.joining(";"));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        ConstraintViolationException exception) {
                
                String code = ConstraintViolationException.class.getName();

                String message = exception.getConstraintViolations().stream().map(constraintViolation -> {
                        return constraintViolation.getPropertyPath().toString();
                }).collect(Collectors.joining(";"));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }

        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        NoSuchElementException exception) {
                
                String code = NoSuchElementException.class.getName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exceptionResponse);
        }

        @ExceptionHandler(EntityExistsException.class)
        public ResponseEntity<ExceptionResponse> handleException(EntityExistsException exception) {
                String code = EntityExistsException.class.getName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(exceptionResponse);
        }
}
