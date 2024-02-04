package com.padr.gys.infra.inbound.amqp.carrier.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.port.EmailServicePort;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEmailUseCase {

    private final EmailServicePort emailServicePort;

    public void execute(SendEmailTransactionModel transactionModel) {
        emailServicePort.send(transactionModel.getSubject(), transactionModel.getContent(), transactionModel.getTo(),
                transactionModel.getCc(), transactionModel.getBcc());
    }
}
