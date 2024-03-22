package com.padr.gys.domain.paperwork.port;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface BasicPaperworkServicePort {
    
    File upload(String filePath, MultipartFile multipartFile);
}
