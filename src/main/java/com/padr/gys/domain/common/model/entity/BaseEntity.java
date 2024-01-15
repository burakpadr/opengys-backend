package com.padr.gys.domain.common.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.padr.gys.domain.common.util.IpUtil;
import com.padr.gys.infra.inbound.common.context.UserContext;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
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
    @Builder.Default
    private Boolean isDeleted = false;

    @PrePersist
    private void prePersist() {
        createDate = lastModifiedDate = LocalDateTime.now();
        createdBy = lastModifiedBy = Objects.nonNull(UserContext.getUser()) ? UserContext.getUser().getId() : null;
        ip = IpUtil.getClientIp();
    }

    @PreUpdate
    private void preUpdate() {
        createDate = lastModifiedDate = LocalDateTime.now();
        createdBy = lastModifiedBy = Objects.nonNull(UserContext.getUser()) ? UserContext.getUser().getId() : null;
        ip = IpUtil.getClientIp();
    }
}
