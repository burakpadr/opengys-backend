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
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.common.context.UserContext;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateResetPasswordOtpUseCase {

    private final OtpServicePort otpServicePort;

    private final RabbitTemplate rabbitTemplate;
    private final TemplateEngine templateEngine;

    public void execute() {
        String userEmail = UserContext.getUser().getEmail(); 

        Otp otp = otpServicePort.create(OtpType.PASSWORD_RESET, userEmail);

        Context context = new Context();

        context.setVariable("otp", otp.getOtp());

        String emailContent = templateEngine.process(EmailTemplate.PASSWORD_RESET_OTP.getTemplateName(), context);

        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();
        sendEmailTransactionModel.setSubject(EmailTemplate.PASSWORD_RESET_OTP.getSubject());
        sendEmailTransactionModel.setTo(List.of(userEmail).toArray(new String[1]));
        sendEmailTransactionModel.setContent(emailContent);

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);
    }
}
