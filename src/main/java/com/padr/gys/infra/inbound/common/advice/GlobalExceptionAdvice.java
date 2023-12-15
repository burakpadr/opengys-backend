package com.padr.gys.infra.inbound.common.advice;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.infra.inbound.common.response.ExceptionResponse;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionAdvice {

        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ExceptionResponse> handleException(BusinessException exception) {
                exception.printStackTrace();

                String code = BusinessException.class.getSimpleName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
                exception.printStackTrace();

                String code = RuntimeException.class.getSimpleName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        MethodArgumentNotValidException exception) {

                exception.printStackTrace();

                String code = MethodArgumentNotValidException.class.getSimpleName();

                Optional<FieldError> fieldErrorOptional = exception.getBindingResult().getFieldErrors().stream()
                                .findFirst();

                String message = "";

                if (fieldErrorOptional.isPresent())
                        message = fieldErrorOptional.get().getField() + " alanı boş bırakılamaz!";

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        ConstraintViolationException exception) {

                exception.printStackTrace();

                String code = ConstraintViolationException.class.getSimpleName();

                Optional<ConstraintViolation<?>> constraintViolationOptional = exception.getConstraintViolations()
                                .stream()
                                .findFirst();

                String message = "";

                if (constraintViolationOptional.isPresent())
                        message = constraintViolationOptional.get().getPropertyPath().toString()
                                        + " alanı boş bırakılamaz!";

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder().code(code).message(message).build());
        }

        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<ExceptionResponse> handleException(
                        NoSuchElementException exception) {

                exception.printStackTrace();

                String code = NoSuchElementException.class.getSimpleName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exceptionResponse);
        }

        @ExceptionHandler(EntityExistsException.class)
        public ResponseEntity<ExceptionResponse> handleException(EntityExistsException exception) {
                exception.printStackTrace();

                String code = EntityExistsException.class.getSimpleName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(exceptionResponse);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ExceptionResponse> handleException(IllegalArgumentException exception) {
                exception.printStackTrace();

                String code = IllegalArgumentException.class.getSimpleName();

                ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                                .code(code)
                                .message(exception.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exceptionResponse);
        }
}
