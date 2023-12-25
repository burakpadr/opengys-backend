package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.rbac.entity.UIElement;

public interface UIElementPersistencePort {
    
    Page<UIElement> findAll(Pageable pageable);

    Optional<UIElement> findById(Long id);

    Optional<UIElement> findByComponentName(String componentName);
    
    UIElement save(UIElement uiElement);
}
