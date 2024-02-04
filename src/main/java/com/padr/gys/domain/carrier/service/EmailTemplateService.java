package com.padr.gys.domain.carrier.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;
import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.outbound.persistence.carrier.port.EmailTemplatePersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class EmailTemplateService implements EmailTemplateServicePort {

    private final EmailTemplatePersistencePort emailTemplatePersistencePort;

    private final MessageSource messageSource;

    @Override
    public EmailTemplate create(EmailTemplate emailTemplate) {
        emailTemplatePersistencePort.findByCode(emailTemplate.getCode()).ifPresent(et -> {
            String[] exceptionArgs = { emailTemplate.getCode().name() };

            throw new EntityExistsException(messageSource.getMessage("carrier.email-template.already-exit-by-code",
                    exceptionArgs, LocaleContextHolder.getLocale()));
        });

        return emailTemplatePersistencePort.save(emailTemplate);
    }

    @Override
    public Page<EmailTemplate> findAll(Pageable pageable) {
        return emailTemplatePersistencePort.findAll(pageable);
    }

    @Override
    public EmailTemplate findById(Long id) {
        return emailTemplatePersistencePort.findById(id).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("carrier.email-template.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public EmailTemplate findByCode(EmailTemplateCode code) {
        return emailTemplatePersistencePort.findByCode(code).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("carrier.email-template.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public EmailTemplate update(Long id, EmailTemplate updateEmailTemplate) {
        EmailTemplate emailTemplate = findById(id);

        Optional<EmailTemplate> duplicatedEmailTemplateOptional = emailTemplatePersistencePort
                .findByCode(updateEmailTemplate.getCode());

        if (duplicatedEmailTemplateOptional.isPresent())
            if (!id.equals(duplicatedEmailTemplateOptional.get().getId())) {
                String[] exceptionArgs = { duplicatedEmailTemplateOptional.get().getCode().name() };

                throw new EntityExistsException(messageSource.getMessage("carrier.email-template.already-exit-by-code",
                        exceptionArgs, LocaleContextHolder.getLocale()));
            }

        emailTemplate.setCode(updateEmailTemplate.getCode());
        emailTemplate.setLabel(updateEmailTemplate.getLabel());
        emailTemplate.setSubject(updateEmailTemplate.getSubject());
        emailTemplate.setContent(updateEmailTemplate.getContent());

        return emailTemplatePersistencePort.save(emailTemplate);
    }

    @Override
    public void delete(Long id) {
        EmailTemplate emailTemplate = findById(id);

        emailTemplate.setIsDeleted(true);

        emailTemplatePersistencePort.save(emailTemplate);
    }
}
