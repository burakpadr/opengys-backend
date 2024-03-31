package com.padr.gys.infra.outbound.persistence.archive.port;

import com.padr.gys.domain.archive.entity.Archive;

public interface ArchivePersistencePort {
    
    Archive save(Archive archive);
}
