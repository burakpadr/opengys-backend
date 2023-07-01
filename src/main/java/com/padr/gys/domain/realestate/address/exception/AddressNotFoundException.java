package com.padr.gys.domain.realestate.address.exception;

import com.padr.gys.domain.common.exception.BaseException;

public class AddressNotFoundException extends BaseException {
    
    private static final String CODE = "ADDRESS_NOT_FOUND_EXCEPTION";

    public AddressNotFoundException(Long id) {
        super(CODE, String.format("%d id'li adres nesnesi bulunamadı!", id));
    }
}
