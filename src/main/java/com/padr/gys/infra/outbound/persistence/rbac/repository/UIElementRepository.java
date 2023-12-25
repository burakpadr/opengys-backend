package com.padr.gys.infra.outbound.persistence.rbac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.rbac.entity.UIElement;

@Repository
public interface UIElementRepository extends JpaRepository<UIElement, Long> {
    
    Optional<UIElement> findByComponentName(String componentName);
}
