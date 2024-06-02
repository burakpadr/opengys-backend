package com.padr.gys.infra.inbound.rest.rentalcontract.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.user.entity.Tenant;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class RentalContractResponse {

    private Long id;
    private String tenantFullName;
    private BigDecimal monthlyRentFee;
    private Integer rentalPaymentDay;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private String rentalContractFileRelativeUrl;
    private Boolean isPublished;
    private Boolean isUpdatable;

    public static RentalContractResponse of(RentalContract rentalContract) {
        Optional<Tenant> tenantOptional = Optional.ofNullable(rentalContract.getTenant());

        Optional<Archive> rentalContractFileOptional = Optional
                .ofNullable(rentalContract.getRentalContractFile());

        return RentalContractResponse.builder()
                .id(rentalContract.getId())
                .tenantFullName(tenantOptional.isPresent() ? tenantOptional.get().getUser().getFullName() : null)
                .monthlyRentFee(rentalContract.getMonthlyRentFee())
                .rentalPaymentDay(rentalContract.getRentalPaymentDay())
                .startDate(rentalContract.getStartDate())
                .endDate(rentalContract.getEndDate())
                .rentalContractFileRelativeUrl(
                        rentalContractFileOptional.isPresent() ? rentalContract.getRentalContractFile().getPath()
                                : null)
                .isPublished(rentalContract.getIsPublished())
                .isUpdatable(rentalContract.getIsUpdatable())
                .build();
    }
}
