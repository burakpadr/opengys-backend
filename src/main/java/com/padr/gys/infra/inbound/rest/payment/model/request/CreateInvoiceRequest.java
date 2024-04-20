package com.padr.gys.infra.inbound.rest.payment.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.realestate.entity.RealEstate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    
    @NotNull
    private InvoiceType type;

    @NotNull
    private LocalDate dateOfInvoice;

    @NotEmpty
    private String currencyCode;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Positive
    private Long realEstateId;

    public Invoice to(RealEstate realEstate) {
        return Invoice.builder()
                .type(type)
                .dateOfInvoice(dateOfInvoice)
                .currencyCode(currencyCode)
                .amount(amount)
                .realEstate(realEstate)
                .build();
    }
}
