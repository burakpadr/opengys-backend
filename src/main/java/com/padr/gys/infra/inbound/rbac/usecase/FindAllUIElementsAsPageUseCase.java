package com.padr.gys.infra.inbound.rbac.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.inbound.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllUIElementsAsPageUseCase {

    private final UIElementServicePort uiElementServicePort;

    public Page<UIElementResponse> execute(Pageable pageable) {
        return uiElementServicePort.findAll(pageable).map(UIElementResponse::of);
    }
}
