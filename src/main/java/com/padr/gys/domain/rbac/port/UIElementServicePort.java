package com.padr.gys.domain.rbac.port;

import java.util.List;

import com.padr.gys.domain.rbac.entity.UIElement;

public interface UIElementServicePort {
    
    List<UIElement> findAll();

    UIElement findById(Long id);

    List<UIElement> createAll(List<UIElement> uiElements);

    UIElement update(UIElement oldUIElement, UIElement newUIElement);

    void delete(UIElement uiElement);
}
