package com.padr.gys.domain.user.port;

import com.padr.gys.domain.user.entity.User;

public interface UserServicePort {

    User create(User user);

    User findById(Long id);

    User findByEmail(String email);

    User update(User oldUser, User updateUser);

    void delete(Long id);
}
