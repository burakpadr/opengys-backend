package com.padr.gys.domain.common.model.property;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StoragePropertyModel {
    
    private String baseUrl;
    private String basePath;

    // Real Estate Properties

    private String realEstateImagesPath;
    private String realEstateImagesRelativeUrl;

    // Rental Contract Properties

    private String rentalContractFilesPath;
    private String rentalContractFilesRelativeUrl;
}

