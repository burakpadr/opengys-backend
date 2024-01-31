package com.padr.gys.infra.inbound.rest.carrier.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteEmailTemplateUseCase {
    
    private final EmailTemplateServicePort emailTemplateServicePort;

    public void execute(Long id) {
        emailTemplateServicePort.delete(id);
    }
}
