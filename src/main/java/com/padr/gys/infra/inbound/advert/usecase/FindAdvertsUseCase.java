package com.padr.gys.infra.inbound.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.infra.inbound.advert.model.response.AdvertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAdvertsUseCase {

    private final AdvertServicePort advertServicePort;

    public Page<AdvertResponse> execute(Long realEstateId, Pageable pageable) {
        Page<Advert> adverts = advertServicePort.findByRealEstateIdAndIsActive(realEstateId, true, pageable);

        List<AdvertResponse> advertResponses = adverts.getContent().stream().map(AdvertResponse::of)
                .toList();

        return new PageImpl<>(advertResponses, pageable, adverts.getTotalElements());
    }
}
