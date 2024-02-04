package com.padr.gys.infra.outbound.persistence.carrier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    
    Optional<EmailTemplate> findByCode(EmailTemplateCode code);
}
