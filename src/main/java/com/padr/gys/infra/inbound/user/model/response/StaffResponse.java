package com.padr.gys.infra.inbound.user.model.response;

import com.padr.gys.domain.user.entity.Staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffResponse {
    
    private Long id;
    private UserResponse user;
    private Boolean isDeedOwner;
    private Boolean isActive;

    public static StaffResponse of(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .user(UserResponse.of(staff.getUser()))
                .isDeedOwner(staff.getIsDeedOwner())
                .isActive(staff.getIsActive())
                .build();
    }
}
