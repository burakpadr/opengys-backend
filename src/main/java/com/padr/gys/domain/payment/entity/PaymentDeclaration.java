package com.padr.gys.domain.payment.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.user.entity.Tenant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment_declarations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class PaymentDeclaration extends BaseEntity {

    @Id
    @SequenceGenerator(name = "payment_declaration_id_seq", sequenceName = "payment_declaration_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_declaration_id_seq")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentDeclarationApprovementStatus approvementStatus;

    @Column
    private Long entityId;

    // This column for history after cancellation process

    @Column
    private LocalDate dateOfInvoicePaid;

    @OneToOne
    private Invoice invoice;

    @ManyToOne
    private Archive receipt;

    @ManyToOne
    private Tenant declarationOwner;
}
