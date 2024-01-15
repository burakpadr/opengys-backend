package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.realestate.entity.RealEstate;

public interface RealEstatePersistencePort {
    
    RealEstate save(RealEstate realEstate);

    List<RealEstate> saveAll(List<RealEstate> realEstates);

    Optional<RealEstate> findById(Long id);

    Page<RealEstate> findAll(Pageable pageable);

    Optional<RealEstate> findByNo(String no);
}
