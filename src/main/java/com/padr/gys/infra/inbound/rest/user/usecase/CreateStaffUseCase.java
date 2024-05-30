package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.padr.gys.domain.carrier.constant.EmailTemplate;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateStaffRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateStaffUseCase {

    private final RolePersistencePort rolePersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final StaffPersistencePort staffPersistencePort;

    private final TemplateEngine templateEngine;
    private final RabbitTemplate rabbitTemplate;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    public StaffResponse execute(CreateStaffRequest request) {
        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(messageSource.getMessage("user.role-id-cannot-be-empty", null,
                        LocaleContextHolder.getLocale()));

            role = rolePersistencePort.findById(request.getUser().getRoleId())
                    .orElseThrow(() -> new NoSuchElementException(
                            messageSource.getMessage("rbac.role.not-found", null, LocaleContextHolder.getLocale())));
        }

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
                .role(role)
                .build();

        if (Objects.nonNull(request.getUser().getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userPersistencePort.save(user);

        // Create staff

        Staff staff = Staff.builder()
                .user(user)
                .isDeedOwner(request.getIsDeedOwner())
                .build();

        staffPersistencePort.save(staff);

        // Send email to the staff

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
