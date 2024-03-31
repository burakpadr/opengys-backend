package com.padr.gys.domain.archive.entity;

import org.hibernate.annotations.SQLRestriction;

import com.padr.gys.domain.common.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "archives")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted=false")
public class Archive extends BaseEntity {

    @Id
    @SequenceGenerator(name = "archive_id_seq", sequenceName = "archive_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archive_id_seq")
    private Long id;

    @Column
    private String path;
}
