package com.padr.gys.infra.inbound.common.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.UserServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServicePort userServicePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServicePort.findByEmail(username);

        return new org.springframework.security.core.userdetails.User(user.getId().toString(),
                user.getPassword(), List.of());

    }

}
