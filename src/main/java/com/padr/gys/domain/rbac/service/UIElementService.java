package com.padr.gys.domain.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.rbac.constant.RbacExceptionMessage;
import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UIElementService implements UIElementServicePort {

    private final UIElementPersistencePort uiElementPersistencePort;

    @Override
    public List<UIElement> findAll() {
        return uiElementPersistencePort.findAll();
    }

    @Override
    public UIElement findById(Long id) {
        return uiElementPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(RbacExceptionMessage.UI_ELEMENT_NOT_FOUND));
    }

    @Override
    public List<UIElement> createAll(List<UIElement> uiElements) {
        List<UIElement> uiElementsWillBeCreated = new ArrayList<>();

        uiElements.stream().forEach(uiElement -> {
            Optional<UIElement> isDuplicatedUIElementOptional =  uiElementPersistencePort
                .findByComponentName(uiElement.getComponentName());

            if (!isDuplicatedUIElementOptional.isPresent())
                uiElementsWillBeCreated.add(uiElement);
        });

        return uiElementPersistencePort.saveAll(uiElementsWillBeCreated);
    }

    @Override
    public UIElement update(UIElement oldUIElement, UIElement newUIElement) {
        oldUIElement.setComponentName(newUIElement.getComponentName());
        oldUIElement.setLabel(newUIElement.getLabel());

        return uiElementPersistencePort.save(oldUIElement);
    }

    @Override
    public void delete(UIElement uiElement) {
        uiElement.setIsDeleted(true);

        uiElementPersistencePort.save(uiElement);
    }
}
