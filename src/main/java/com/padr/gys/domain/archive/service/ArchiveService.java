package com.padr.gys.domain.archive.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.domain.common.util.FileUtil;
import com.padr.gys.infra.outbound.persistence.archive.port.ArchivePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ArchiveService implements ArchiveServicePort {

    private final ArchivePersistencePort archivePersistencePort;

    private final FileUtil fileUtil;

    @Override
    public Archive create(MultipartFile multipartFile, Long entityId, String basePath, String baseUrl) {
        String fileName = UUID.randomUUID().toString();
        String extension = fileUtil.getFileExtension(multipartFile.getOriginalFilename());

        String absolutePath = String.format("%s/%d/%s.%s", basePath,
                entityId,
                fileName, extension);

        fileUtil.upload(absolutePath, multipartFile);

        String relativePath = baseUrl + "/" + entityId + "/" + fileName + "." + extension;

        Archive archive = Archive.builder()
                .path(relativePath)
                .build();

        return archivePersistencePort.save(archive);
    }
}
