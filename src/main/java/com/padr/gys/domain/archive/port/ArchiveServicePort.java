package com.padr.gys.domain.archive.port;

import com.padr.gys.domain.archive.constant.ArchiveType;
import com.padr.gys.domain.archive.entity.Archive;

public interface ArchiveServicePort {
    
    Archive create(Archive archive);

    Archive findByArchiveTypeAndEntityId(ArchiveType archiveType, Long entityId);
}
