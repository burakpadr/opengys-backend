package com.padr.gys.infra.inbound.rest.carrier.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.entity.EmailTemplate;
import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.inbound.rest.carrier.model.request.EmailTemplateRequest;
import com.padr.gys.infra.inbound.rest.carrier.model.response.EmailTemplateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateEmailTemplateUseCase {

    private final EmailTemplateServicePort emailTemplateServicePort;

    public EmailTemplateResponse execute(EmailTemplateRequest request) {
        EmailTemplate emailTemplate = EmailTemplate.builder()
                .code(request.getCode())
                .label(request.getLabel())
                .subject(request.getSubject())
                .content(request.getContent())
                .build();

        return EmailTemplateResponse.of(emailTemplateServicePort.create(emailTemplate));
    }
}
