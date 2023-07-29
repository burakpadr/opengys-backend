package com.padr.gys.infra.inbound.user.adapter;

import com.padr.gys.infra.inbound.user.model.request.UserRequest;
import com.padr.gys.infra.inbound.user.model.response.UserResponse;
import com.padr.gys.infra.inbound.user.usecase.CreateUserUseCase;
import com.padr.gys.infra.inbound.user.usecase.DeleteUserUseCase;
import com.padr.gys.infra.inbound.user.usecase.FindUserByIdUseCase;
import com.padr.gys.infra.inbound.user.usecase.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAdapter {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return findUserByIdUseCase.execute(id);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return createUserUseCase.execute(request.to());
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,
                               @Valid @RequestBody UserRequest request) {
        return updateUserUseCase.execute(id, request.to());
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        deleteUserUseCase.execute(id);
    }
}
