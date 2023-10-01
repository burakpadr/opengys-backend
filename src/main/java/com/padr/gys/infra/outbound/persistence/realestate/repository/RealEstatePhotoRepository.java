package com.padr.gys.infra.outbound.persistence.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

@Repository
public interface RealEstatePhotoRepository extends JpaRepository<RealEstatePhoto, Long> {
    
    List<RealEstatePhoto> findByRealEstateId(Long realEstateId);
}
