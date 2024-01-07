package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.infra.outbound.persistence.rbac.repository.UIElementRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class UIElementPersistencePortImpl implements UIElementPersistencePort {
    
    private final UIElementRepository uiElementRepository;

    @Override
    public List<UIElement> findAll() {
        return uiElementRepository.findAll();
    }

    @Override
    public Optional<UIElement> findById(Long id) {
        return uiElementRepository.findById(id);
    }

    @Override
    public Optional<UIElement> findByComponentName(String componentName) {
        return uiElementRepository.findByComponentName(componentName);
    }

    @Override
    public List<UIElement> saveAll(List<UIElement> uiElements) {
        return uiElementRepository.saveAll(uiElements);
    }

    @Override
    public UIElement save(UIElement uiElement) {
        return uiElementRepository.save(uiElement);
    }
}
