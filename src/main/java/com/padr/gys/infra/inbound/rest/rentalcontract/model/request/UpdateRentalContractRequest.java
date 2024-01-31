package com.padr.gys.infra.inbound.rest.rentalcontract.model.request;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalContractRequest {

    @NotNull
    @NotEmpty
    private String tenantTitle;

    @NotNull
    @NotEmpty
    private String tenantIdentityNumber;

    @NotNull
    private BigDecimal monthlyRentFee;

    @NotNull
    @NotEmpty
    private String currencyCodeOfRentFee;

    @NotNull
    private Integer rentalPaymentDay;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Boolean isPublished;

    public RentalContract to() {
        return RentalContract.builder()
                .tenantTitle(tenantTitle)
                .tenantIdentityNumber(tenantIdentityNumber)
                .monthlyRentFee(monthlyRentFee)
                .currencyCodeOfRentFee(currencyCodeOfRentFee)
                .rentalPaymentDay(rentalPaymentDay)
                .startDate(startDate)
                .endDate(endDate)
                .isPublished(isPublished)
                .build();
    }
}
