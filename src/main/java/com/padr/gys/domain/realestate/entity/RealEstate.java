package com.padr.gys.domain.realestate.entity;

import java.util.List;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.categorization.entity.persistence.Category;
import com.padr.gys.domain.categorization.entity.persistence.SubCategory;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.ForSaleSubStatus;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.status.constant.SubStatus;

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

    @ManyToOne
    private Category category;

    @ManyToOne
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

    @OneToMany(mappedBy = "realEstate")
    private List<RealEstatePhoto> realEstatePhotos;

    @OneToMany(mappedBy = "realEstate")
    @Transient
    private List<Advert> adverts;

    @OneToMany(mappedBy = "realEstate")
    @Transient
    private List<RentalContract> rentalContracts;

    public RealEstatePhoto getCoverPhoto() {
        return realEstatePhotos
                .stream()
                .parallel()
                .filter(RealEstatePhoto::getIsCover)
                .findFirst()
                .orElse(null);
    }

    public SubStatus getSubStatus() {
        return switch (mainStatus) {
            case FOR_RENT -> forRentSubStatus;
            case FOR_SALE -> forSaleSubStatus;
        };
    }
}
