package com.padr.gys.infra.outbound.persistence.carrier.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;
import com.padr.gys.infra.outbound.persistence.carrier.repository.EmailTemplateRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class EmailTemplatePersistencePortImpl implements EmailTemplatePersistencePort {
    
    private final EmailTemplateRepository emailTemplateRepository;

    @Override
    public Page<EmailTemplate> findAll(Pageable pageable) {
        return emailTemplateRepository.findAll(pageable);
    }

    @Override
    public Optional<EmailTemplate> findById(Long id) {
        return emailTemplateRepository.findById(id);
    }

    @Override
    public Optional<EmailTemplate> findByCode(EmailTemplateCode code) {
        return emailTemplateRepository.findByCode(code);
    }

    @Override
    public EmailTemplate save(EmailTemplate emailTemplate) {
        return emailTemplateRepository.save(emailTemplate);
    }
}
