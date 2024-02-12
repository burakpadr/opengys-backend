package com.padr.gys.domain.carrier.service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.carrier.port.EmailServicePort;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class EmailService implements EmailServicePort {

    private final JavaMailSender mailSender;

    @Override
    public void send(String subject, String content, String[] to, String[] cc, String[] bcc) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.displayName());
    
            helper.setSubject(subject);
            helper.setTo(to);

            if (Objects.nonNull(cc))
                helper.setCc(cc);

            if (Objects.nonNull(bcc))
                helper.setBcc(bcc);

            helper.setText(content, true);
    
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
