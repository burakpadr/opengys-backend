package com.padr.gys.infra.outbound.persistence.categorization.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.categorization.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c "
            + "WHERE c.name ILIKE concat('%', :searchTerm, '%') ")
    Page<Category> findBySearchTerm(String searchTerm, Pageable pageable);
}
