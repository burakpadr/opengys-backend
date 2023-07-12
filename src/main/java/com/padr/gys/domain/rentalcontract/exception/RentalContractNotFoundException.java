package com.padr.gys.domain.rentalcontract.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class RentalContractNotFoundException extends BaseException {

    private static final String CODE = "RENTAL_CONTRACT_NOT_FOUND_EXCEPTION";
    private static final String MESSAGE = "Kiralik sözleşme kaydı bulunamadı!";

    public RentalContractNotFoundException() {
        super(CODE, MESSAGE);
    }
}
