package com.padr.gys.infra.inbound.rest.rbac.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.domain.rbac.port.UIElementServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteUIElementUseCase {
    
    private UIElementServicePort uiElementServicePort;

    public void execute(Long id) {
        UIElement uiElement = uiElementServicePort.findById(id);

        uiElementServicePort.delete(uiElement);
    }
}
