package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

public interface RealEstatePhotoPersistencePort {

    List<RealEstatePhoto> saveAll(List<RealEstatePhoto> realEstatePhotos);

    List<RealEstatePhoto> findByRealEstateId(Long realEstateId);

    Optional<RealEstatePhoto> findById(Long id);
}
