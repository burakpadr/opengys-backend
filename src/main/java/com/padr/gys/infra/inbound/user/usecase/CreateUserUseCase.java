package com.padr.gys.infra.inbound.user.usecase;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.user.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserServicePort userServicePort;

    public UserResponse execute(User user) {
        return UserResponse.of(userServicePort.create(user));
    }
}
