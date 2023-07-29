package com.padr.gys.infra.inbound.user.model.request;

import com.padr.gys.domain.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String identityNumber;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public User to() {
        return User.builder()
                .title(title)
                .identityNumber(identityNumber)
                .email(email)
                .password(password)
                .build();
    }
}
