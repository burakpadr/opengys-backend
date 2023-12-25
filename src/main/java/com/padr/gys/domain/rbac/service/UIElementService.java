package com.padr.gys.domain.rbac.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.rbac.constant.RbacExceptionMessage;
import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UIElementService implements UIElementServicePort {

    private final UIElementPersistencePort uiElementPersistencePort;

    @Override
    public Page<UIElement> findAll(Pageable pageable) {
        return uiElementPersistencePort.findAll(pageable);
    }

    @Override
    public UIElement findById(Long id) {
        return uiElementPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(RbacExceptionMessage.UI_ELEMENT_NOT_FOUND));
    }

    @Override
    public UIElement create(UIElement uiElement) {
        uiElementPersistencePort
                .findByComponentName(uiElement.getComponentName()).ifPresent((ue) -> {
                    throw new EntityExistsException(RbacExceptionMessage.UI_ELEMENT_ALREADY_EXIST_ASSOCIATED_WITH_NAME);
                });

        return uiElementPersistencePort.save(uiElement);
    }

    @Override
    public UIElement update(UIElement oldUIElement, UIElement newUIElement) {
        oldUIElement.setComponentName(newUIElement.getComponentName());
        oldUIElement.setLabel(newUIElement.getLabel());

        return uiElementPersistencePort.save(oldUIElement);
    }

    @Override
    public void delete(UIElement uiElement) {
        uiElement.setIsActive(false);

        uiElementPersistencePort.save(uiElement);
    }
}
