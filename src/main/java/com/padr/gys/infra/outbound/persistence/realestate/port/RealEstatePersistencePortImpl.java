package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.information.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.realestate.repository.RealEstateRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealEstatePersistencePortImpl implements RealEstatePersistencePort {
    
    private final RealEstateRepository realEstateRepository;

    @Override
    public RealEstate save(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public Optional<RealEstate> findByIdAndIsActive(Long id, Boolean isActive) {
        return realEstateRepository.findByIdAndIsActive(id, isActive);
    }

    @Override
    public Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable) {
        return realEstateRepository.findByIsActive(isActive, pageable);
    }

    @Override
    public Optional<RealEstate> findByNoAndIsActive(String no, Boolean isActive) {
        return realEstateRepository.findByNoAndIsActive(no, isActive);
    }
}
