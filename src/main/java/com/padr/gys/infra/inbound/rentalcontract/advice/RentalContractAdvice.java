package com.padr.gys.infra.inbound.rentalcontract.advice;

import com.padr.gys.domain.common.model.response.ExceptionResponse;
import com.padr.gys.domain.rentalcontract.exception.RentalContractAlreadyExistException;
import com.padr.gys.domain.rentalcontract.exception.RentalContractNotFoundException;
import com.padr.gys.infra.inbound.common.advice.BaseAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.padr.gys.infra.inbound")
public class RentalContractAdvice extends BaseAdvice {

    @ExceptionHandler(RentalContractNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(RentalContractNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateExceptionResponse(exception));
    }

    @ExceptionHandler(RentalContractAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleException(RentalContractAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(generateExceptionResponse(exception));
    }
}
