package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.constant.EnumRentPaymentStatusStatisticElementType;
import com.padr.gys.domain.dashboard.constant.EnumRentalIncomeStatisticElementType;
import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ApprovePaymentDeclarationUseCase {

    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long paymentDeclarationId) {
        PaymentDeclaration paymentDeclaration = paymentDeclarationPersistencePort.findById(paymentDeclarationId).orElseThrow(
                () -> new NoSuchElementException(messageSource.getMessage("payment.payment-declaration.not-found", null,
                        LocaleContextHolder.getLocale())));

        paymentDeclaration.setApprovementStatus(PaymentDeclarationApprovementStatus.APPROVED);
        paymentDeclarationPersistencePort.save(paymentDeclaration);

        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.PENDING);

        DashboardHandlerContext.getDashboardHandler(RentalIncomeStatistic.class)
                .updateStatisticElement(EnumRentalIncomeStatisticElementType.MONTHLY);
    }
}
