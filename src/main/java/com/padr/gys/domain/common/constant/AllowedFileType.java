package com.padr.gys.domain.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AllowedFileType {
    
    IMAGE_JPEG("jpeg"),
    IMAGE_JPG("jpg"),
    IMAGE_PNG("png"),
    FILE_PDF("pdf"),
    FILE_DOCX("docx");

    private final String extension;
}
