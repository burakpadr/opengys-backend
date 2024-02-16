package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;
import java.util.Objects;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.padr.gys.domain.carrier.constant.EmailTemplate;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateStaffRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateStaffUseCase {

    private final UserServicePort userServicePort;
    private final StaffServicePort staffServicePort;
    private final RoleServicePort roleServicePort;

    private final TemplateEngine templateEngine;

    private final RabbitTemplate rabbitTemplate;

    private final MessageSource messageSource;

    public StaffResponse execute(CreateStaffRequest request) {
        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(messageSource.getMessage("user.role-id-cannot-be-empty", null,
                        LocaleContextHolder.getLocale()));

            role = roleServicePort.findById(request.getUser().getRoleId());
        }

        User user = userServicePort.create(request.getUser().to(role));
        Staff staff = staffServicePort.create(request.to(user));

        Context context = new Context();

        String emailContent = templateEngine.process(EmailTemplate.STAFF_ACCOUNT_HAS_BEEN_CREATED.getTemplateName(),
                context);

        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();
        sendEmailTransactionModel.setSubject(EmailTemplate.STAFF_ACCOUNT_HAS_BEEN_CREATED.getSubject());
        sendEmailTransactionModel.setTo(List.of(user.getEmail()).toArray(new String[1]));
        sendEmailTransactionModel.setContent(emailContent);

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);

        return StaffResponse.of(staff);
    }
}
