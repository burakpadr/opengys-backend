package com.padr.gys.infra.outbound.persistence.user.port;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.outbound.persistence.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistencePortImpl implements UserPersistencePort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmailAndIsActive(String email,
                                                 Boolean isActive) {
        return userRepository.findByEmailAndIsActive(email, isActive);
    }

    @Override
    public Optional<User> findByIdentityNumberAndIsActive(String identityNumber,
                                                          Boolean isActive) {
        return userRepository.findByIdentityNumberAndIsActive(identityNumber,
                isActive);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
