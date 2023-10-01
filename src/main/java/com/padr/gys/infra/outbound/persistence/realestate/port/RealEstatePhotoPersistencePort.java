package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.List;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

public interface RealEstatePhotoPersistencePort {

    List<RealEstatePhoto> saveAll(List<RealEstatePhoto> realEstatePhotos);

    List<RealEstatePhoto> findByRealEstateId(Long realEstateId);
}
