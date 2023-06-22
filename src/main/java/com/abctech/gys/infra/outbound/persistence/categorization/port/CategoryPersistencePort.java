package com.abctech.gys.infra.outbound.persistence.categorization.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abctech.gys.domain.categorization.entity.Category;

public interface CategoryPersistencePort {

    Page<Category> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<Category> findByIdAndIsActive(Long id, Boolean isActive);

    Category save(Category category);
}
