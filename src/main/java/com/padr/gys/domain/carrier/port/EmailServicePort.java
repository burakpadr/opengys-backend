package com.padr.gys.domain.carrier.port;

public interface EmailServicePort {
    
    void send(String subject, String content, String[] to, String[] cc, String[] bcc);
}
