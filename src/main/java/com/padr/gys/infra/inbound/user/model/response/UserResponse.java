package com.padr.gys.infra.inbound.user.model.response;

import com.padr.gys.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String title;
    private String identityNumber;
    private String email;
    private String password;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .title(user.getTitle())
                .identityNumber(user.getIdentityNumber())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
