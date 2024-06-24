package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.realestate.repository.RealEstateRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RealEstatePersistencePortImpl implements RealEstatePersistencePort {
    
    private final RealEstateRepository realEstateRepository;

    @Override
    public RealEstate save(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public List<RealEstate> saveAll(List<RealEstate> realEstates) {
        return realEstateRepository.saveAll(realEstates);
    }

    @Override
    public Optional<RealEstate> findById(Long id) {
        return realEstateRepository.findById(id);
    }

    @Override
    public Page<RealEstate> findAll(Pageable pageable) {
        return realEstateRepository.findAll(pageable);
    }

    @Override
    public Optional<RealEstate> findByNo(String no) {
        return realEstateRepository.findByNo(no);
    }

    @Override
    public Page<RealEstate> findBySearchTerm(String searchTerm, Pageable pageable) {
        return realEstateRepository.findBySearchTerm(searchTerm, pageable);
    }
}
