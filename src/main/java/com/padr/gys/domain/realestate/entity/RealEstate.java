package com.padr.gys.domain.realestate.entity;

import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import jakarta.persistence.*;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.status.constant.MainStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "real_estates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class RealEstate extends BaseEntity {

    @Id
    @SequenceGenerator(name = "real_estate_id_seq", sequenceName = "real_estate_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "real_estate_id_seq")
    private Long id;

    @Column
    private String no;

    @ManyToOne
    private Category category;

    @ManyToOne
    private SubCategory subCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private MainStatus mainStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    private RealEstatePhoto coverPhoto;

    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    private List<RealEstatePhoto> realEstatePhotos;

    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @Transient
    private List<Advert> adverts;

    @OneToMany(mappedBy = "realEstate", fetch = FetchType.LAZY)
    @Transient
    private List<RentalContract> rentalContracts;
}
