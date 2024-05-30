package com.padr.gys.infra.inbound.rest.user.model.request;

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
public class UpdateStaffRequest {
    
    @NotNull
    @Valid
    private UpdateUserRequest user;

    @NotNull
    private Boolean isDeedOwner;
}
