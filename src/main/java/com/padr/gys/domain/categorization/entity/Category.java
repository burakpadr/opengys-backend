package com.padr.gys.domain.categorization.entity;

import java.util.List;

import com.padr.gys.domain.realestate.entity.RealEstate;
import jakarta.persistence.*;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.common.model.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Audited
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_deleted=false")
public class Category extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<RealEstate> realEstates;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<SubCategory> subCategories;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Attribute> attributes;
}
