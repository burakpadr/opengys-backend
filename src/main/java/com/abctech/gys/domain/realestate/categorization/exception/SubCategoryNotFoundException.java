package com.abctech.gys.domain.realestate.categorization.exception;

import com.abctech.gys.domain.common.exception.BaseException;

public class SubCategoryNotFoundException extends BaseException {

    private static final String CODE = "SUB_CATEGORY_NOT_FOUND_EXCEPTION";

    public SubCategoryNotFoundException(Long subCategoryId) {
        super(CODE, String.format("%d id'li alt kategori bulunamadı!", subCategoryId));
    }
}
