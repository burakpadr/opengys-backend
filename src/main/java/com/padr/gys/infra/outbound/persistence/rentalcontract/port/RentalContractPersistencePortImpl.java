package com.padr.gys.infra.outbound.persistence.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.infra.outbound.persistence.rentalcontract.repository.RentalContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentalContractPersistencePortImpl implements  RentalContractPersistencePort {

    private final RentalContractRepository rentalContractRepository;

    @Override
    public Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable) {
        return rentalContractRepository.findByRealEstateId(realEstateId, pageable);
    }

    @Override
    public List<RentalContract> findByRealEstateId(Long realEstateId) {
        return rentalContractRepository.findByRealEstateId(realEstateId);
    }

    @Override
    public Optional<RentalContract> findById(Long id) {
        return rentalContractRepository.findById(id);
    }

    @Override
    public RentalContract save(RentalContract rentalContract) {
        return rentalContractRepository.save(rentalContract);
    }

    @Override
    public List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished) {
        return rentalContractRepository.findByRealEstateIdAndIsPublished(realEstateId, isPublished);
    }
}
