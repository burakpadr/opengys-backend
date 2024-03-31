package com.padr.gys.domain.archive.port;

import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;

public interface ArchiveServicePort {
    
    Archive create(MultipartFile multipartFile, Long entityId, String basePath, String baseUrl);
}
