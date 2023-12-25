package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.infra.outbound.persistence.rbac.repository.UIElementRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class UIElementPersistencePortImpl implements UIElementPersistencePort {
    
    private final UIElementRepository uiElementRepository;

    @Override
    public Page<UIElement> findAll(Pageable pageable) {
        return uiElementRepository.findAll(pageable);
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
    public UIElement save(UIElement uiElement) {
        return uiElementRepository.save(uiElement);
    }
}
