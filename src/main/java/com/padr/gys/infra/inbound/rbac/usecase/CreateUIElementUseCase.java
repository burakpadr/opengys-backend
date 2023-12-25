package com.padr.gys.infra.inbound.rbac.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.inbound.rbac.model.request.UIElementRequest;
import com.padr.gys.infra.inbound.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateUIElementUseCase {

    private final UIElementServicePort uiElementServicePort;

    public UIElementResponse execute(UIElementRequest request) {
        UIElement uiElement = UIElement.builder()
                .componentName(request.getComponentName())
                .label(request.getLabel())
                .build();

        return UIElementResponse.of(uiElementServicePort.create(uiElement));
    }
}
