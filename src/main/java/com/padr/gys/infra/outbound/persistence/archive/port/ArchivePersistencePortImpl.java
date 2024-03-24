package com.padr.gys.infra.outbound.persistence.archive.port;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.archive.constant.ArchiveType;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.infra.outbound.persistence.archive.repository.ArchiveRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ArchivePersistencePortImpl implements ArchivePersistencePort {
    
    private final ArchiveRepository archiveRepository;

    @Override
    public Archive save(Archive archive) {
        return archiveRepository.save(archive);
    }

    @Override
    public Optional<Archive> findByArchiveTypeAndEntityId(ArchiveType archiveType, Long entityId) {
        return archiveRepository.findByArchiveTypeAndEntityId(archiveType, entityId);
    }
}
