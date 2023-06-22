package com.abctech.gys.domain.realestate.categorization.entity;

import org.hibernate.envers.Audited;

import com.abctech.gys.domain.common.model.entity.BaseEntity;

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
@Audited
@Table(name = "sub_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SubCategory extends BaseEntity {
    
    @Id
    @SequenceGenerator(name = "sub_category_id_seq", sequenceName = "sub_category_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_id_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private Category category;
}
