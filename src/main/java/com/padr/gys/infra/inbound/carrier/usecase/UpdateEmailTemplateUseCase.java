package com.padr.gys.infra.inbound.carrier.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.entity.EmailTemplate;
import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.inbound.carrier.model.request.EmailTemplateRequest;
import com.padr.gys.infra.inbound.carrier.model.response.EmailTemplateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateEmailTemplateUseCase {

    private final EmailTemplateServicePort emailTemplateServicePort;

    public EmailTemplateResponse execute(Long id, EmailTemplateRequest request) {
        EmailTemplate updateEmailTemplate = EmailTemplate.builder()
                .code(request.getCode())
                .label(request.getLabel())
                .content(request.getContent())
                .build();

        return EmailTemplateResponse.of(emailTemplateServicePort.update(id, updateEmailTemplate));
    }
}
