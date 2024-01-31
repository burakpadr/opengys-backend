package com.padr.gys.infra.inbound.carrier.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.carrier.model.request.EmailTemplateRequest;
import com.padr.gys.infra.inbound.carrier.model.response.EmailTemplateResponse;
import com.padr.gys.infra.inbound.carrier.usecase.CreateEmailTemplateUseCase;
import com.padr.gys.infra.inbound.carrier.usecase.DeleteEmailTemplateUseCase;
import com.padr.gys.infra.inbound.carrier.usecase.FindEmailTemplateUseCase;
import com.padr.gys.infra.inbound.carrier.usecase.FindEmailTemplatesAsPageUseCase;
import com.padr.gys.infra.inbound.carrier.usecase.UpdateEmailTemplateUseCase;

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

    private final JavaMailSender mailSender;

    @PostMapping
    public EmailTemplateResponse create(@Valid @RequestBody EmailTemplateRequest request) {
        return createEmailTemplateUseCase.execute(request);
    }

    @GetMapping("/as-page")
    public Page<EmailTemplateResponse> findAll(Pageable pageable) {
        return findEmailTemplatesAsPageUseCase.execute(pageable);
    }

    @GetMapping
    public EmailTemplateResponse find(@RequestParam Optional<Long> id, @RequestParam Optional<String> code) {
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

    @GetMapping("/deneme")
    public void deneme() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("burakpadr99@gmail.com");
        message.setSubject("subject");
        message.setText("body2");

        mailSender.send(message);
    }

}
