package com.padr.gys.infra.inbound.rest.advert.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.padr.gys.infra.inbound.rest.advert.model.request.CreateAdvertRequest;
import com.padr.gys.infra.inbound.rest.advert.model.request.UpdateAdvertRequest;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;
import com.padr.gys.infra.inbound.rest.advert.usecase.*;

@RestController
@RequestMapping("/gys/api/v1/adverts")
@RequiredArgsConstructor
public class AdvertAdapter {

    private final FindAdvertsUseCase findAdvertsUseCase;
    private final FindAdvertByIdUseCase findAdvertByIdUseCase;
    private final CreateAdvertUseCase createAdvertUseCase;
    private final UpdateAdvertUseCase updateAdvertUseCase;
    private final DeleteAdvertUseCase deleteAdvertUseCase;

    @GetMapping
    public Page<AdvertResponse> findAll(@RequestParam Long realEstateId, Pageable pageable) {
        return findAdvertsUseCase.execute(realEstateId, pageable);
    }

    @PostMapping
    public AdvertResponse create(@Valid @RequestBody CreateAdvertRequest request) {
        return createAdvertUseCase.execute(request);
    }

    @GetMapping("/{advertId}")
    public AdvertResponse findById(@PathVariable Long advertId) {
        return findAdvertByIdUseCase.execute(advertId);
    }

    @PutMapping("/{advertId}")
    public AdvertResponse update(@PathVariable Long advertId, @Valid @RequestBody UpdateAdvertRequest request) {
        return updateAdvertUseCase.execute(advertId, request);
    }

    @DeleteMapping("/{advertId}")
    public void delete(@PathVariable Long advertId) {
        deleteAdvertUseCase.execute(advertId);
    }
}
