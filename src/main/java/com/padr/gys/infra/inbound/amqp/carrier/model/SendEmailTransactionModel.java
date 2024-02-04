package com.padr.gys.infra.inbound.amqp.carrier.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailTransactionModel implements Serializable {
    
    private String subject;
    private String content;
    private String[] to;
    private String[] cc;
    private String[] bcc;    
}
