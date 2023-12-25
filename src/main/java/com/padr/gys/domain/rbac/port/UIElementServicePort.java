package com.padr.gys.domain.rbac.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.rbac.entity.UIElement;

public interface UIElementServicePort {
    
    Page<UIElement> findAll(Pageable pageable);

    UIElement findById(Long id);

    UIElement create(UIElement uiElement);

    UIElement update(UIElement oldUIElement, UIElement newUIElement);

    void delete(UIElement uiElement);
}
