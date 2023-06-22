package com.abctech.gys.domain.realestate.categorization.exception;

import com.abctech.gys.domain.common.exception.BaseException;

public class CategoryNotFoundException extends BaseException {

    private final static String CODE = "CATEGORY_NOT_FOUND_EXCEPTION";

    public CategoryNotFoundException(Long categoryId) {
        super(CODE, String.format("%d id'li bir kategori bulunamadı", categoryId));
    }
}
