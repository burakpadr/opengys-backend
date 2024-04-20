package com.padr.gys.domain.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.realestate.entity.RealEstate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class Invoice extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "invoice_id_seq", sequenceName = "invoice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_id_seq")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @Column
    private LocalDate dateOfInvoice;

    @Column
    private String currencyCode;

    @Column
    private BigDecimal amount;

    @ManyToOne
    private RealEstate realEstate;

    @ManyToOne
    private PaymentDeclaration paymentDeclaration;
}
