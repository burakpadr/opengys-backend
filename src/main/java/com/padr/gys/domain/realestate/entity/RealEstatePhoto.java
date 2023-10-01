package com.padr.gys.domain.realestate.entity;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.padr.gys.domain.common.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Audited
@Table(name = "real_estate_photos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_active=true")
public class RealEstatePhoto extends BaseEntity {

    @Id
    @SequenceGenerator(name = "real_estate_photo_id_seq", sequenceName = "real_estate_photo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "real_estate_photo_id_seq")
    private Long id;

    @Transient
    private String extension;

    @Transient
    private String contentBase64;

    @Column
    private String sftpPath;

    @Column
    private String path;

    @Column
    private Boolean isCover;

    @ManyToOne
    private RealEstate realEstate;
}
