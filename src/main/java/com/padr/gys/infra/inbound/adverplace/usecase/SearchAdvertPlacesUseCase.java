package com.padr.gys.infra.inbound.adverplace.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchAdvertPlacesUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public Page<AdvertPlaceResponse> execute(String searchTerm, Pageable pageable) {
        SearchHits<AdvertPlaceElasticsearch> advertPlaces = advertPlaceServicePort.search(searchTerm, pageable);

        List<AdvertPlaceResponse> advertPlaceResponses = advertPlaces.getSearchHits().stream().map(SearchHit::getContent)
                .map(AdvertPlaceResponse::of).toList();

        return new PageImpl<>(advertPlaceResponses, pageable, advertPlaces.getTotalHits());

    }
}
