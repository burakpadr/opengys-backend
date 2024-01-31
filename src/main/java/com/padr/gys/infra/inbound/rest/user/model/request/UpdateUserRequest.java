package com.padr.gys.infra.inbound.rest.user.model.request;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @NotEmpty
    private String name;

    private String surname;

    @NotEmpty
    @Email
    private String email;
    private Long roleId;

    public User to(Role role) {
        return User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .role(role)
                .build();
    }
}
