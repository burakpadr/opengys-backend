package com.padr.gys.infra.outbound.persistence.archive.port;

import org.springframework.stereotype.Component;

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
}
