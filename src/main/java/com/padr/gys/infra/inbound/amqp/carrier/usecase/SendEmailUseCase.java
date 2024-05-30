package com.padr.gys.infra.inbound.amqp.carrier.usecase;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEmailUseCase {

    private final JavaMailSender mailSender;

    public void execute(SendEmailTransactionModel transactionModel) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.displayName());

            helper.setSubject(transactionModel.getSubject());
            helper.setTo(transactionModel.getTo());

            if (Objects.nonNull(transactionModel.getCc()))
                helper.setCc(transactionModel.getCc());

            if (Objects.nonNull(transactionModel.getBcc()))
                helper.setBcc(transactionModel.getBcc());

            helper.setText(transactionModel.getContent(), true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
