package com.padr.gys.domain.rentalcontract.entity;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.user.entity.Tenant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "rental_contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class RentalContract extends BaseEntity {

    @Id
    @SequenceGenerator(name = "rental_contract_id_seq", sequenceName = "rental_contract_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_contract_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Tenant tenant;

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
    private Archive rentalContractFile;

    @ManyToOne
    private RealEstate realEstate;
}
