package com.padr.gys.infra.inbound.rest.realestate.adapter;

import java.util.List;

import com.padr.gys.infra.inbound.rest.realestate.usecase.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.infra.inbound.rest.realestate.model.request.CreateRealEstateRequest;
import com.padr.gys.infra.inbound.rest.realestate.model.request.UpdateRealEstateRequest;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateDetailResponse;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstatePhotoResponse;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/real-estates")
@RequiredArgsConstructor
public class RealEstateAdapter {

    private final CreateRealEstateUseCase createRealEstateUseCase;
    private final FindRealEstatesUseCase findRealEstatesUseCase;
    private final FindRealEstateByIdUseCase findRealEstateByIdUseCase;
    private final UpdateRealEstateUseCase updateRealEstateUseCase;
    private final DeleteRealEstateUseCase deleteRealEstateUseCase;
    private final ChangeRealEstateCoverPhotoUseCase changeRealEstateCoverPhotoUseCase;
    private final CreateRealEstatePhotosUseCase createRealEstatePhotosUseCase;
    private final FindRealEstatePhotosUseCase findRealEstatePhotosUseCase;
    private final DeleteRealEstatePhotoUseCase deleteRealEstatePhotoUseCase;
    private final SearchRealEstateUseCase searchRealEstateUseCase;

    @GetMapping
    public Page<RealEstateResponse> findAll(Pageable pageable) {
        return findRealEstatesUseCase.execute(pageable);
    }

    @PostMapping
    public RealEstateResponse create(@Validated @RequestBody CreateRealEstateRequest request) {
        return createRealEstateUseCase.execute(request);
    }

    @GetMapping("/{realEstateId}")
    public RealEstateDetailResponse findById(@PathVariable Long realEstateId) {
        return findRealEstateByIdUseCase.execute(realEstateId);
    }

    @PutMapping("/{realEstateId}")
    public RealEstateDetailResponse update(@PathVariable Long realEstateId,
            @Validated @RequestBody UpdateRealEstateRequest request) {
        return updateRealEstateUseCase.execute(realEstateId, request);
    }

    @DeleteMapping("/{realEstateId}")
    public void delete(@PathVariable Long realEstateId) {
        deleteRealEstateUseCase.execute(realEstateId);
    }

    @PatchMapping("/{realEstateId}/change-cover-photo")
    public void delete(@PathVariable Long realEstateId, @RequestParam Long coverPhotoId) {
        changeRealEstateCoverPhotoUseCase.execute(realEstateId, coverPhotoId);
    }

    @PostMapping("/{realEstateId}/photos")
    public void createRealEstatePhotos(@PathVariable Long realEstateId, @RequestParam List<MultipartFile> images) {
        createRealEstatePhotosUseCase.execute(realEstateId, images);
    }

    @GetMapping("/{realEstateId}/photos")
    public List<RealEstatePhotoResponse> findRealEstatePhotos(@PathVariable Long realEstateId) {
        return findRealEstatePhotosUseCase.execute(realEstateId);
    }

    @DeleteMapping("/{realEstateId}/photos/{realEstatePhotoId}")
    public void deleteRealEstatePhoto(@PathVariable Long realEstateId, @PathVariable Long realEstatePhotoId) {
        deleteRealEstatePhotoUseCase.execute(realEstateId, realEstatePhotoId);
    }

    @GetMapping("/search")
    public Page<RealEstateResponse> findBySearchTerm(@RequestParam("search") String searchTerm, Pageable pageable) {
        return searchRealEstateUseCase.execute(searchTerm, pageable);
    }
}
