package com.padr.gys.infra.inbound.rest.carrier.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.infra.inbound.rest.carrier.model.request.EmailTemplateRequest;
import com.padr.gys.infra.inbound.rest.carrier.model.response.EmailTemplateResponse;
import com.padr.gys.infra.inbound.rest.carrier.usecase.CreateEmailTemplateUseCase;
import com.padr.gys.infra.inbound.rest.carrier.usecase.DeleteEmailTemplateUseCase;
import com.padr.gys.infra.inbound.rest.carrier.usecase.FindEmailTemplateUseCase;
import com.padr.gys.infra.inbound.rest.carrier.usecase.FindEmailTemplatesAsPageUseCase;
import com.padr.gys.infra.inbound.rest.carrier.usecase.UpdateEmailTemplateUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/email-templates")
@RequiredArgsConstructor
public class EmailTemplateAdapter {

    private final CreateEmailTemplateUseCase createEmailTemplateUseCase;
    private final FindEmailTemplatesAsPageUseCase findEmailTemplatesAsPageUseCase;
    private final FindEmailTemplateUseCase findEmailTemplateUseCase;
    private final UpdateEmailTemplateUseCase updateEmailTemplateUseCase;
    private final DeleteEmailTemplateUseCase deleteEmailTemplateUseCase;

    @PostMapping
    public EmailTemplateResponse create(@Valid @RequestBody EmailTemplateRequest request) {
        return createEmailTemplateUseCase.execute(request);
    }

    @GetMapping("/as-page")
    public Page<EmailTemplateResponse> findAll(Pageable pageable) {
        return findEmailTemplatesAsPageUseCase.execute(pageable);
    }

    @GetMapping
    public EmailTemplateResponse find(@RequestParam Optional<Long> id, @RequestParam Optional<EmailTemplateCode> code) {
        return findEmailTemplateUseCase.execute(id, code);
    }

    @PutMapping("/{id}")
    public EmailTemplateResponse update(@PathVariable Long id, @Valid @RequestBody EmailTemplateRequest request) {
        return updateEmailTemplateUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteEmailTemplateUseCase.execute(id);
    }
}
