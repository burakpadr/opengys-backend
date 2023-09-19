package com.padr.gys.domain.advertplace.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;
import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;

public interface AdvertPlaceServicePort {

    Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable);

    Page<AdvertPlaceElasticsearch> search(String searchTerm, Pageable pageable);

    AdvertPlace findByIdAndIsActive(Long id, Boolean isActive);

    AdvertPlace create(AdvertPlace advertPlace);

    AdvertPlace update(Long id, AdvertPlace advertPlace);

    void delete(Long id);
}
