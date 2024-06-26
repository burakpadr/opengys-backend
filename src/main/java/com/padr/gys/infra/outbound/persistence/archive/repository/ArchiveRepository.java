package com.padr.gys.infra.outbound.persistence.archive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.archive.entity.Archive;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
