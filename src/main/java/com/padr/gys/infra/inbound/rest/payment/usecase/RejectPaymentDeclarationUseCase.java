package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.constant.EnumRentPaymentStatusStatisticElementType;
import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class RejectPaymentDeclarationUseCase {

    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long id) {
        PaymentDeclaration paymentDeclaration = paymentDeclarationPersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(messageSource.getMessage("payment.payment-declaration.not-found", null,
                        LocaleContextHolder.getLocale())));

        paymentDeclaration.setApprovementStatus(PaymentDeclarationApprovementStatus.REJECTED);
        paymentDeclaration.setDateOfInvoicePaid(paymentDeclaration.getInvoice().getDateOfInvoice());
        paymentDeclaration.setInvoice(null);

        paymentDeclarationPersistencePort.save(paymentDeclaration);

        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.PENDING);

        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.UNPAID);
    }
}
