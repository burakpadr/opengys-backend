package com.padr.gys.domain.archive.service;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.archive.constant.ArchiveType;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.infra.outbound.persistence.archive.port.ArchivePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ArchiveService implements ArchiveServicePort {

    private final ArchivePersistencePort archivePersistencePort;

    private final MessageSource messageSource;

    @Override
    public Archive create(Archive archive) {
        return archivePersistencePort.save(archive);
    }

    @Override
    public Archive findByArchiveTypeAndEntityId(ArchiveType archiveType, Long entityId) {
        return archivePersistencePort.findByArchiveTypeAndEntityId(archiveType, entityId).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("archive.not-found", null, LocaleContextHolder.getLocale())));
    }
}
