package com.padr.gys.infra.inbound.carrier.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.inbound.carrier.model.response.EmailTemplateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindEmailTemplatesAsPageUseCase {
    
    private final EmailTemplateServicePort emailTemplateServicePort;

    public Page<EmailTemplateResponse> execute(Pageable pageable) {
        return emailTemplateServicePort.findAll(pageable).map(EmailTemplateResponse::of);
    }
}
