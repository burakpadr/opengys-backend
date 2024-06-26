package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAdvertsUseCase {

    private final AdvertPersistencePort advertPersistencePort;

    public Page<AdvertResponse> execute(Long realEstateId, Pageable pageable) {
        Page<Advert> adverts = advertPersistencePort.findByRealEstateId(realEstateId, pageable);

        List<AdvertResponse> advertResponses = adverts.getContent().stream().map(AdvertResponse::of)
                .toList();

        return new PageImpl<>(advertResponses, pageable, adverts.getTotalElements());
    }
}
