package com.padr.gys.infra.outbound.persistence.categorization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.realestate.categorization.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    List<Category> findByIsActive(Boolean isActive);

    Page<Category> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<Category> findByIdAndIsActive(Long id, Boolean isActive);
}
