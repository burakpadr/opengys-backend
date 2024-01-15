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
class UserService implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        throwExceptionIfEmailIsDuplicated(user.getEmail());
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.save(user);
    }

    @Override
    public User findById(Long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public User findByEmail(String email) {
        return userPersistencePort.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public User update(User oldUser, User updateUser) {
        if (!oldUser.getEmail().equals(updateUser.getEmail()))
            throwExceptionIfEmailIsDuplicated(updateUser.getEmail());

        oldUser.setEmail(oldUser.getEmail());
        oldUser.setPassword(passwordEncoder.encode(oldUser.getPassword()));

        return userPersistencePort.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);

        user.setIsDeleted(true);

        userPersistencePort.save(user);
    }

    private void throwExceptionIfEmailIsDuplicated(String email) {
        userPersistencePort.findByEmail(email)
                .ifPresent(u -> {
                    throw new EntityExistsException(UserExceptionMessage.USER_ALREADY_EXIST);
                });

    }
}
