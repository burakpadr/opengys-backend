package com.padr.gys.infra.inbound.common.advice;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.common.model.response.ExceptionResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionAdvice {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
                String code = "RUNTIME_EXCEPTION";

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        MethodArgumentNotValidException exception) {
                String code = "METHOD_ARGUMENT_NOT_VALID_EXCEPTION";
                String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getField)
                                .collect(Collectors.joining(";"));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        ConstraintViolationException exception) {
                String code = "CONSTRAINT_VIOLATION_EXCEPTION";
                String message = exception.getConstraintViolations().stream().map(constraintViolation -> {
                        return constraintViolation.getPropertyPath().toString();
                }).collect(Collectors.joining(";"));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }
}
