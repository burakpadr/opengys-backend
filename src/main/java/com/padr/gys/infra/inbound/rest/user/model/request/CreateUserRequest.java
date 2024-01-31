package com.padr.gys.infra.inbound.rest.user.model.request;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotEmpty
    private String name;

    private String surname;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

    private Long roleId;

    public User to(Role role) {
        return User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }
}
