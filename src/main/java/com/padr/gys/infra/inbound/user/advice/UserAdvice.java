package com.padr.gys.infra.inbound.user.advice;

import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.domain.user.exception.UserAlreadyExistException;
import com.padr.gys.domain.user.exception.UserNotFoundException;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class UserAdvice extends BaseAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(generateExceptionResponse(exception));
    }
}
