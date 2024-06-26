package com.padr.gys.infra.outbound.persistence.user.port;

import com.padr.gys.domain.user.entity.User;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);
}
