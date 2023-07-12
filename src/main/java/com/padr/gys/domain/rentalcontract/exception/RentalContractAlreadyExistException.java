package com.padr.gys.domain.rentalcontract.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class RentalContractAlreadyExistException extends BaseException {

    private static final String CODE = "RENTAL_CONTRACT_ALREADY_EXIST_EXCEPTION";
    private static final String MESSAGE = "Aktif kira sözleşmesi var!";

    public RentalContractAlreadyExistException() {
        super(CODE, MESSAGE);
    }
}
