package com.padr.gys.infra.outbound.persistence.user.repository;

import com.padr.gys.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsActive(String email,
                                          Boolean isActive);

    Optional<User> findByIdentityNumberAndIsActive(String identityNumber,
                                                   Boolean isActive);
}
