package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.padr.gys.domain.carrier.constant.EmailTemplate;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateTenantUseCase {

    private final TenantPersistencePort tenantPersistencePort;
    private final UserPersistencePort userPersistencePort;

    private final TemplateEngine templateEngine;
    private final RabbitTemplate rabbitTemplate;
    private final MessageSource messageSource;

    public TenantResponse execute(CreateTenantRequest request) {

        // Create user

        userPersistencePort.findByEmail(request.getUser().getEmail())
                .ifPresent(u -> {
                    throw new EntityExistsException(
                            messageSource.getMessage("user.already-exist", null, LocaleContextHolder.getLocale()));
                });

        User user = User.builder()
                .name(request.getUser().getName())
                .surname(request.getUser().getSurname())
                .email(request.getUser().getEmail())
                .build();

        userPersistencePort.save(user);

        // Create tenant

        Tenant tenant = Tenant.builder()
                .user(user)
                .build();

        tenantPersistencePort.save(tenant);

        // Create email content

        Context context = new Context();

        String emailContent = templateEngine.process(EmailTemplate.TENANT_ACCOUNT_HAS_BEEN_CREATED.getTemplateName(),
                context);

        // Create email transaction model

        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();
        sendEmailTransactionModel.setSubject(EmailTemplate.TENANT_ACCOUNT_HAS_BEEN_CREATED.getSubject());
        sendEmailTransactionModel.setTo(List.of(user.getEmail()).toArray(new String[1]));
        sendEmailTransactionModel.setContent(emailContent);

        // Send the prepared email template to the queue

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);

        return TenantResponse.of(tenant);
    }
}
