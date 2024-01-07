package com.padr.gys.infra.inbound.rbac.usecase;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UIElementResponse> execute(List<UIElementRequest> request) {
        List<UIElement> uiElements = request.stream().map(element -> {
            return UIElement.builder()
                    .componentName(element.getComponentName())
                    .label(element.getLabel())
                    .build();
        }).collect(Collectors.toList());

        return uiElementServicePort.createAll(uiElements).stream().map(UIElementResponse::of).toList();
    }
}
