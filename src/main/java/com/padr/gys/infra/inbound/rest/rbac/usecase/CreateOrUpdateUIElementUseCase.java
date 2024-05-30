package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.UIElement;
import com.padr.gys.infra.inbound.rest.rbac.model.request.UIElementRequest;
import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateOrUpdateUIElementUseCase {

    private final UIElementPersistencePort uiElementPersistencePort;

    public List<UIElementResponse> execute(List<UIElementRequest> request) {
        List<UIElement> uiElements = request.stream().map(element -> {
            return UIElement.builder()
                    .componentName(element.getComponentName())
                    .label(element.getLabel())
                    .build();
        }).collect(Collectors.toList());

        return uiElementPersistencePort.saveAll(uiElements).stream().map(UIElementResponse::of).toList();
    }
}
