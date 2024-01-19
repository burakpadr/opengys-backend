package com.padr.gys.infra.inbound.user.model.request;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStaffRequest {
    
    @NotNull
    @Valid
    private CreateUserRequest user;

    @NotNull
    private Boolean isDeedOwner;

    public Staff to(User user) {
        return Staff.builder()
            .user(user)
            .isDeedOwner(isDeedOwner)
            .build();
    }   
}
