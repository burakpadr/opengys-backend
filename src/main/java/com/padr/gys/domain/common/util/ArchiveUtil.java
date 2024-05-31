package com.padr.gys.domain.common.util;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArchiveUtil {

    private final FileUtil fileUtil;

    public Archive prepareArchive(MultipartFile multipartFile, Long entityId, String basePath, String baseUrl) {
        String fileName = UUID.randomUUID().toString();
        String extension = fileUtil.getFileExtension(multipartFile.getOriginalFilename());

        String absolutePath = String.format("%s/%d/%s.%s", basePath,
                entityId,
                fileName, extension);

        fileUtil.upload(absolutePath, multipartFile);

        String relativePath = baseUrl + "/" + entityId + "/" + fileName + "." + extension;

        return Archive.builder()
                .path(relativePath)
                .build();
    }
}
