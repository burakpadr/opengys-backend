package com.padr.gys.infra.inbound.rest.rentalcontract.model.response;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class RentalContractResponse {

    private Long id;
    private BigDecimal monthlyRentFee;
    private String currencyCodeOfRentFee;
    private Integer rentalPaymentDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPublished;

    public static RentalContractResponse of(RentalContract rentalContract) {
        return RentalContractResponse.builder()
                .id(rentalContract.getId())
                .monthlyRentFee(rentalContract.getMonthlyRentFee())
                .currencyCodeOfRentFee(rentalContract.getCurrencyCodeOfRentFee())
                .rentalPaymentDay(rentalContract.getRentalPaymentDay())
                .startDate(rentalContract.getStartDate())
                .endDate(rentalContract.getEndDate())
                .isPublished(rentalContract.getIsPublished())
                .build();
    }
}
