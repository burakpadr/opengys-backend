package com.padr.gys.infra.inbound.rest.rbac.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindUIElementByIdUseCase {
    
    private UIElementServicePort uiElementServicePort;

    public UIElementResponse execute(Long id) {
        return UIElementResponse.of(uiElementServicePort.findById(id));
    }
}
