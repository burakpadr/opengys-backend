package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.rbac.entity.UIElement;

public interface UIElementPersistencePort {
    
    List<UIElement> findAll();

    Optional<UIElement> findById(Long id);

    Optional<UIElement> findByComponentName(String componentName);
    
    UIElement save(UIElement uiElement);

    List<UIElement> saveAll(List<UIElement> uiElements);
}
