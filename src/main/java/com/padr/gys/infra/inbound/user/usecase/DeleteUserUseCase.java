package com.padr.gys.infra.inbound.user.usecase;

import com.padr.gys.domain.user.port.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserServicePort userServicePort;

    public void execute(Long id) {
        userServicePort.delete(id);
    }
}
