package com.padr.gys.infra.inbound.user.model.request;

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
public class UserRequest {

    @NotEmpty
    @Size(min = 12, max = 12)
    private String phoneNumber;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

    public User to() {
        return User.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .build();
    }
}
