package com.padr.gys.domain.attribute.entity;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Audited
@Table(name = "attribute_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_deleted=false")
public class AttributeValue extends BaseEntity {

    @Id
    @SequenceGenerator(name = "attribute_value_id_seq", sequenceName = "attribute_value_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_value_id_seq")
    private Long id;

    @Column
    private String value;

    @ManyToOne
    private Attribute attribute;
}
