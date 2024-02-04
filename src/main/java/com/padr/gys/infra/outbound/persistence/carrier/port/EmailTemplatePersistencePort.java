package com.padr.gys.infra.outbound.persistence.carrier.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;

public interface EmailTemplatePersistencePort {
    
    Page<EmailTemplate> findAll(Pageable pageable);

    Optional<EmailTemplate> findById(Long id);

    Optional<EmailTemplate> findByCode(EmailTemplateCode code);

    EmailTemplate save(EmailTemplate emailTemplate);
}
