package com.padr.gys.infra.inbound.rbac.model.response;

import com.padr.gys.domain.rbac.entity.UIElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UIElementResponse {
    
    private Long id;
    private String componentName;
    private String label;

    public static UIElementResponse of(UIElement uiElement) {
        return UIElementResponse.builder()
                .id(uiElement.getId())
                .componentName(uiElement.getComponentName())
                .label(uiElement.getLabel())
                .build();
    }
}
