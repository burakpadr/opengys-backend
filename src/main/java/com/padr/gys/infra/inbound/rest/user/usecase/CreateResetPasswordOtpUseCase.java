package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.padr.gys.domain.carrier.constant.EmailTemplate;
import com.padr.gys.domain.otp.constant.OtpType;
import com.padr.gys.domain.otp.entity.Otp;
import com.padr.gys.domain.otp.port.OtpServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateResetPasswordOtpRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateResetPasswordOtpUseCase {

    private final OtpServicePort otpServicePort;
    private final UserServicePort userServicePort;

    private final RabbitTemplate rabbitTemplate;
    private final TemplateEngine templateEngine;

    public void execute(CreateResetPasswordOtpRequest request) {
        userServicePort.findByEmail(request.getEmail());

        Otp otp = otpServicePort.create(OtpType.PASSWORD_RESET, request.getEmail());

        Context context = new Context();

        context.setVariable("otp", otp.getOtp());

        String emailContent = templateEngine.process(EmailTemplate.PASSWORD_RESET_OTP.getTemplateName(), context);

        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();
        sendEmailTransactionModel.setSubject(EmailTemplate.PASSWORD_RESET_OTP.getSubject());
        sendEmailTransactionModel.setTo(List.of(request.getEmail()).toArray(new String[1]));
        sendEmailTransactionModel.setContent(emailContent);

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);
    }
}
