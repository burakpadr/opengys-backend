package com.padr.gys.domain.realestate.categorization.entity;

import java.util.List;

import org.hibernate.envers.Audited;

import com.padr.gys.domain.common.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    @Transient
    private List<SubCategory> subCategories;
}
