package com.padr.gys.domain.carrier.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;

public interface EmailTemplateServicePort {
    
    EmailTemplate create(EmailTemplate emailTemplate);

    Page<EmailTemplate> findAll(Pageable pageable);

    EmailTemplate findById(Long id);

    EmailTemplate findByCode(EmailTemplateCode code);

    EmailTemplate update(Long id, EmailTemplate updateEmailTemplate);

    void delete(Long id);
}
