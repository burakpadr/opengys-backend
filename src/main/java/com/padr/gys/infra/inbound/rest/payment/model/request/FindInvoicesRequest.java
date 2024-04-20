package com.padr.gys.infra.inbound.rest.payment.model.request;

import com.padr.gys.domain.payment.constant.InvoiceType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindInvoicesRequest {
    
    private InvoiceType type;

    @NotNull
    @Positive
    private Long realEstateId;
}
