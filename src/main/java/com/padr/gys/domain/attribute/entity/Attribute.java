package com.padr.gys.domain.attribute.entity;

import java.util.List;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.common.model.entity.BaseEntity;
import com.padr.gys.domain.frontend.constant.InputType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Audited
@Table(name = "attributes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_active=true")
public class Attribute extends BaseEntity {

    @Id
    @SequenceGenerator(name = "attribute_id_seq", sequenceName = "attribute_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_id_seq")
    private Long id;

    @Column
    private String alias;

    @Column
    private String label;

    @Column
    private Integer screenOrder;

    @Column
    @Enumerated(EnumType.STRING)
    private InputType inputType;

    @ManyToOne
    private Category category;

    @ManyToOne
    private SubCategory subCategory;

    @OneToMany(mappedBy = "attribute")
    private List<AttributeValue> attributeValues;
}
