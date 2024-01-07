package com.padr.gys.infra.inbound.rbac.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.inbound.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllUIElementsUseCase {

    private final UIElementServicePort uiElementServicePort;

    public List<UIElementResponse> execute() {
        return uiElementServicePort.findAll().stream().map(UIElementResponse::of).toList();
    }
}
