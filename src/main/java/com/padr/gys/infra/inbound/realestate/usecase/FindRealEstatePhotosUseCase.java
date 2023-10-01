package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstatePhotoResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstatePhotosUseCase {

    @Value(value = "${sftp.storage.remoteBasePath}")
    private String remoteBasePath;

    @Value(value = "${static-server.url}")
    private String staticServerUrl;

    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public List<RealEstatePhotoResponse> execute(Long realEstateId) {
        return realEstatePhotoServicePort.findByRealEstateId(realEstateId).stream().map(RealEstatePhotoResponse::of)
                .toList();
    }
}
