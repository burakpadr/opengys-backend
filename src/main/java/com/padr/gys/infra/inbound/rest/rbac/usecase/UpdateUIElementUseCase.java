package com.padr.gys.infra.inbound.rest.rbac.usecase;

import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.infra.inbound.rest.rbac.model.request.UIElementRequest;
import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class UpdateUIElementUseCase {
    
    private final UIElementPersistencePort uiElementPersistencePort;

    private final MessageSource messageSource;

    public UIElementResponse execute(Long id, UIElementRequest request) {
        UIElement oldUIElement = uiElementPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rbac.ui-element.not-found", null, LocaleContextHolder.getLocale())));

        UIElement newUIElement = UIElement.builder()
                .componentName(request.getComponentName())
                .label(request.getLabel())
                .build();

        oldUIElement.setComponentName(newUIElement.getComponentName());
        oldUIElement.setLabel(newUIElement.getLabel());

        return UIElementResponse.of(oldUIElement);
    }
}
