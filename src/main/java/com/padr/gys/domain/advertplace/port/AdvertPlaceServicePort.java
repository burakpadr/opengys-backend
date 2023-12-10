package com.padr.gys.domain.advertplace.port;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

public interface AdvertPlaceServicePort {

    Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable);

    Page<AdvertPlace> search(String searchTerm, Pageable pageable);

    List<AdvertPlace> findByIsActive(Boolean isActive);

    AdvertPlace findByIdAndIsActive(Long id, Boolean isActive);

    AdvertPlace create(AdvertPlace advertPlace);

    AdvertPlace update(Long id, AdvertPlace advertPlace);

    void delete(Long id);
}
