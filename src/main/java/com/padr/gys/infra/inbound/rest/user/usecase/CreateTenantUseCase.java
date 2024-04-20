package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.padr.gys.domain.carrier.constant.EmailTemplate;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateTenantUseCase {

    private final TenantServicePort tenantServicePort;
    private final UserServicePort userServicePort;

    private final TemplateEngine templateEngine;

    private final RabbitTemplate rabbitTemplate;

    public TenantResponse execute(CreateTenantRequest request) {
        User user = userServicePort.create(request.getUser().to(null));

        Tenant tenant = Tenant.builder()
                .user(user)
                .build();

        tenantServicePort.create(tenant);

        // Create email content

        Context context = new Context();

        String emailContent = templateEngine.process(EmailTemplate.TENANT_ACCOUNT_HAS_BEEN_CREATED.getTemplateName(),
                context);

        // Create email transaction model

        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();
        sendEmailTransactionModel.setSubject(EmailTemplate.TENANT_ACCOUNT_HAS_BEEN_CREATED.getSubject());
        sendEmailTransactionModel.setTo(List.of(user.getEmail()).toArray(new String[1]));
        sendEmailTransactionModel.setContent(emailContent);

        // send the prepared email template to the queue

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);

        return TenantResponse.of(tenant);
    }
}
