package com.abctech.gys.domain.common.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abctech.gys.domain.common.util.IpUtil;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @CreatedBy
    private Long createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private Long lastModifiedBy;

    @Column
    private String ip;

    @Column
    private Boolean isActive;

    @PrePersist
    private void prePersist() {
        ip = IpUtil.getClientIp();
    }

    @PreUpdate
    private void preUpdate() {
        ip = IpUtil.getClientIp();
    }
}
