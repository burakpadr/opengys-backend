package com.padr.gys.infra.inbound.rbac.model.response;

import com.padr.gys.domain.rbac.entity.RoleUIElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleUIElementResponse {
    
    private Long roleUIElementId;
    private RoleResponse role;
    private UIElementResponse uiElement;

    public static RoleUIElementResponse of(RoleUIElement roleUIElement) {
        return RoleUIElementResponse.builder()
                .roleUIElementId(roleUIElement.getId())
                .role(RoleResponse.of(roleUIElement.getRole()))
                .uiElement(UIElementResponse.of(roleUIElement.getUiElement()))
                .build();
    }
}
