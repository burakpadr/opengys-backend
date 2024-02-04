package com.padr.gys.infra.inbound.amqp.carrier.adapter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.amqp.carrier.usecase.SendEmailUseCase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailAmqpAdapter {

    private final SendEmailUseCase sendEmailUseCase;
    
    @RabbitListener(queues = "carrier.email.send.queue")
    public void sendEmail(SendEmailTransactionModel transactionModel) {
        sendEmailUseCase.execute(transactionModel);
    }
}
