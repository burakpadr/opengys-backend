package com.padr.gys.infra.inbound.realestate.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.infra.inbound.realestate.model.request.CreateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.model.request.UpdateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstateDetailResponse;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstatePhotoResponse;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstateResponse;
import com.padr.gys.infra.inbound.realestate.usecase.CreateRealEstatePhotosUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.CreateRealEstateUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.DeleteRealEstateUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.FindRealEstateByIdUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.FindRealEstatePhotosUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.FindRealEstatesUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.UpdateRealEstatePhotosUseCase;
import com.padr.gys.infra.inbound.realestate.usecase.UpdateRealEstateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private final CreateRealEstatePhotosUseCase createRealEstatePhotosUseCase;
    private final FindRealEstatePhotosUseCase findRealEstatePhotosUseCase;
    private final UpdateRealEstatePhotosUseCase updateRealEstatePhotosUseCase;

    @GetMapping
    public Page<RealEstateResponse> findAll(Pageable pageable) {
        return findRealEstatesUseCase.execute(pageable);
    }

    @PostMapping
    public RealEstateResponse create(@Valid @RequestBody CreateRealEstateRequest request) {
        return createRealEstateUseCase.execute(request);
    }

    @GetMapping("/{realEstateId}")
    public RealEstateDetailResponse findById(@PathVariable Long realEstateId) {
        return findRealEstateByIdUseCase.execute(realEstateId);
    }

    @PutMapping("/{realEstateId}")
    public RealEstateDetailResponse update(@PathVariable Long realEstateId,
            @Valid @RequestBody UpdateRealEstateRequest request) {
        return updateRealEstateUseCase.execute(realEstateId, request);
    }

    @DeleteMapping("/{realEstateId}")
    public void delete(@PathVariable Long realEstateId) {
        deleteRealEstateUseCase.execute(realEstateId);
    }

    @PostMapping("/{realEstateId}/photos")
    public void createRealEstatePhotos(@PathVariable Long realEstateId, @RequestParam List<MultipartFile> images) {
        createRealEstatePhotosUseCase.execute(realEstateId, images);
    }

    @GetMapping("/{realEstateId}/photos")
    public List<RealEstatePhotoResponse> findRealEstatePhotos(@PathVariable Long realEstateId) {
        return findRealEstatePhotosUseCase.execute(realEstateId);
    }

    @PutMapping("/{realEstateId}/photos")
    public void updateRealEstatePhotos(HttpServletRequest httpServletRequest, @PathVariable Long realEstateId, @RequestParam List<MultipartFile> images) {
        updateRealEstatePhotosUseCase.execute(realEstateId, images);
    }
}
