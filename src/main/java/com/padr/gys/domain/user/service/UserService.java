package com.padr.gys.domain.user.service;

import com.padr.gys.domain.user.constant.UserExceptionMessage;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        throwExceptionIfEmailIsDuplicated(user.getEmail());
        throwExceptionIfIdentityNumberIsDuplicated(user.getIdentityNumber());

        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.save(user);
    }

    @Override
    public User findById(Long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public User findByEmailAndIsActive(String email, Boolean isActive) {
        return userPersistencePort.findByEmailAndIsActive(email, isActive)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public User update(Long id, User user) {
        User oldUser = findById(id);

        if (!oldUser.getEmail().equals(user.getEmail()))
            throwExceptionIfEmailIsDuplicated(user.getEmail());

        if (!oldUser.getIdentityNumber().equals(user.getIdentityNumber()))
            throwExceptionIfIdentityNumberIsDuplicated(user.getIdentityNumber());

        oldUser.setTitle(user.getTitle());
        oldUser.setIdentityNumber(user.getIdentityNumber());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);

        user.setIsActive(false);

        userPersistencePort.save(user);
    }

    private void throwExceptionIfEmailIsDuplicated(String email) {
        userPersistencePort.findByEmailAndIsActive(email, true)
                .ifPresent(u -> {
                    throw new EntityExistsException(UserExceptionMessage.USER_ALREADY_EXIST);
                });

    }

    private void throwExceptionIfIdentityNumberIsDuplicated(String identityNumber) {
        userPersistencePort.findByIdentityNumberAndIsActive(identityNumber,
                        true)
                .ifPresent(u -> {
                    throw new EntityExistsException(UserExceptionMessage.USER_ALREADY_EXIST);
                });
    }
}
