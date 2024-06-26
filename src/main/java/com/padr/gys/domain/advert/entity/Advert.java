package com.padr.gys.domain.advert.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.realestate.entity.RealEstate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "adverts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class Advert extends BaseEntity {

    @Id
    @SequenceGenerator(name = "advert_id_seq", sequenceName = "advert_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advert_id_seq")
    private Long id;

    @ManyToOne
    private AdvertPlace advertPlace;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private BigDecimal price;

    @Column
    private Boolean isPublished;

    @ManyToOne
    private RealEstate realEstate;
}
