package com.padr.gys.infra.inbound.common.security.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistencePort.findByEmail(username)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        return new org.springframework.security.core.userdetails.User(user.getId().toString(),
                user.getPassword(), List.of());

    }

}
