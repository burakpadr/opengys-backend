package com.padr.gys.domain.deedowner.entity;

import org.hibernate.envers.Audited;

import com.padr.gys.domain.common.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Audited
@Table(name = "deed_owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeedOwner extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "deed_owner_id_seq", sequenceName = "deed_owner_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deed_owner_id_seq")
    private Long id;

    @Column
    private String title;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;
}
