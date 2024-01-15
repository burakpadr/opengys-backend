package com.padr.gys.domain.categorization.entity;

import com.padr.gys.domain.realestate.entity.RealEstate;
import jakarta.persistence.*;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.padr.gys.domain.common.model.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Audited
@Table(name = "sub_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_deleted=false")
public class SubCategory extends BaseEntity {

    @Id
    @SequenceGenerator(name = "sub_category_id_seq", sequenceName = "sub_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_id_seq")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "subCategory")
    @Transient
    private List<RealEstate> realEstates;

    @ManyToOne
    private Category category;
}
