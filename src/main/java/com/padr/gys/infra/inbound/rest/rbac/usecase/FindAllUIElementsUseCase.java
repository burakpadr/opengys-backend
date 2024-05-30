package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllUIElementsUseCase {

    private final UIElementPersistencePort uiElementPersistencePort;

    public List<UIElementResponse> execute() {
        return uiElementPersistencePort.findAll().stream().map(UIElementResponse::of).toList();
    }
}
