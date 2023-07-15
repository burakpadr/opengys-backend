package com.padr.gys.domain.rentalcontract.entity;

import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.realestate.entity.RealEstate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Audited
@Table(name = "rental_contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RentalContract extends BaseEntity {

    public RentalContract(RentalContract rentalContract) {
        this.id = rentalContract.getId();
        this.tenantTitle = rentalContract.getTenantTitle();
        this.tenantIdentityNumber = rentalContract.getTenantIdentityNumber();
        this.monthlyRentFee = rentalContract.getMonthlyRentFee();
        this.currencyCodeOfRentFee = rentalContract.getCurrencyCodeOfRentFee();
        this.rentalPaymentDay = rentalContract.getRentalPaymentDay();
        this.startDate = rentalContract.getStartDate();
        this.endDate = rentalContract.getEndDate();
        this.isPublished = rentalContract.getIsPublished();
    }

    @Id
    @SequenceGenerator(name = "rental_contract_id_seq", sequenceName = "rental_contract_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_contract_id_seq")
    private Long id;

    @Column
    private String tenantTitle;

    @Column
    private String tenantIdentityNumber;

    @Column
    private BigDecimal monthlyRentFee;

    @Column
    private String currencyCodeOfRentFee;

    @Column
    private Integer rentalPaymentDay;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Boolean isPublished;

    @ManyToOne
    private RealEstate realEstate;
}
