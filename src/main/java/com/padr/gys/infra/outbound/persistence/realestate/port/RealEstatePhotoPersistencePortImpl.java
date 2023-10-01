package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.infra.outbound.persistence.realestate.repository.RealEstatePhotoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RealEstatePhotoPersistencePortImpl implements RealEstatePhotoPersistencePort {

    private final RealEstatePhotoRepository realEstatePhotoRepository;

    @Override
    public List<RealEstatePhoto> saveAll(List<RealEstatePhoto> realEstatePhotos) {
        return realEstatePhotoRepository.saveAll(realEstatePhotos);
    }

    @Override
    public List<RealEstatePhoto> findByRealEstateId(Long realEstateId) {
        return realEstatePhotoRepository.findByRealEstateId(realEstateId);
    }
}
