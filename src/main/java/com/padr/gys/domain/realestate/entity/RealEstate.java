package com.padr.gys.domain.realestate.entity;

import org.hibernate.envers.Audited;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.ForSaleSubStatus;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.status.constant.SubStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Audited
@Table(name = "real_estates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RealEstate extends BaseEntity {

    @Id
    @SequenceGenerator(name = "real_estate_id_seq", sequenceName = "real_estate_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "real_estate_id_seq")
    private Long id;

    @Column
    private String no;

    @OneToOne
    private Category category;

    @OneToOne
    private SubCategory subCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private MainStatus mainStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private ForSaleSubStatus forSaleSubStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private ForRentSubStatus forRentSubStatus;

    @OneToOne
    private Address address;

    public SubStatus getSubStatus() {
        return switch (mainStatus) {
            case FOR_RENT -> forRentSubStatus;
            case FOR_SALE -> forSaleSubStatus;
        };
    }
}
