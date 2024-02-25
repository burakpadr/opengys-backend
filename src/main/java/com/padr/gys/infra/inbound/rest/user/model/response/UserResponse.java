package com.padr.gys.infra.inbound.rest.user.model.response;

import java.util.Objects;

import com.padr.gys.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String roleLabel;
    private Long roleId;

    public static UserResponse of(User user) {
        boolean roleIsNotNull = Objects.nonNull(user.getRole());

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .roleLabel(roleIsNotNull ? user.getRole().getLabel() : "")
                .roleId(roleIsNotNull ? user.getRole().getId() : null)
                .build();
    }
}
