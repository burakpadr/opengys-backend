package com.padr.gys.infra.inbound.rest.rentalcontract.model.request;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalContractRequest {

    @NotNull
    private BigDecimal monthlyRentFee;

    @NotNull
    private Integer rentalPaymentDay;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Boolean isPublished;

    @NotNull
    @Positive
    private Long realEstateId;

    @NotNull
    @Positive
    private Long tenantId;

    public RentalContract to() {
        return RentalContract.builder()
                .monthlyRentFee(monthlyRentFee)
                .rentalPaymentDay(rentalPaymentDay)
                .startDate(startDate)
                .endDate(endDate)
                .isPublished(isPublished)
                .build();
    }
}
