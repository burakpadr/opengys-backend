package com.padr.gys.domain.carrier.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.carrier.port.EmailServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class EmailService implements EmailServicePort {
    
    private final JavaMailSender mailSender;

    @Override
    public void send(String subject, String content, String[] to, String[] cc, String[] bcc) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setBcc(bcc);
        simpleMailMessage.setText(content);

        mailSender.send(simpleMailMessage);
    }
}
