package com.padr.gys.domain.realestate.port;

import java.util.List;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

public interface RealEstatePhotoServicePort {

    List<RealEstatePhoto> findByRealEstateId(Long realEstateId);
    
    List<RealEstatePhoto> createAll(List<RealEstatePhoto> realEstatePhotos);

    void updateAll(RealEstate realEstate, List<RealEstatePhoto> realEstatePhotos);
}
