package com.padr.gys.infra.outbound.persistence.archive.port;

import java.util.Optional;

import com.padr.gys.domain.archive.constant.ArchiveType;
import com.padr.gys.domain.archive.entity.Archive;

public interface ArchivePersistencePort {
    
    Archive save(Archive archive);

    Optional<Archive> findByArchiveTypeAndEntityId(ArchiveType archiveType, Long entityId);
}
