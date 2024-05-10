package com.padr.gys.domain.user.entity;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class Tenant extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "tenant_id_seq", sequenceName = "tenant_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_id_seq")
    private Long id;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "tenant")
    private RentalContract rentalContract;
}
