package com.padr.gys.domain.advertplace.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertPlaceService implements AdvertPlaceServicePort {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    private final MessageSource messageSource;

    @Override
    public Page<AdvertPlace> findAll(Pageable pageable) {
        return advertPlacePersistencePort.findAll(pageable);
    }

    @Override
    public Page<AdvertPlace> search(String searchTerm, Pageable pageable) {
        return advertPlacePersistencePort.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public List<AdvertPlace> findAll() {
        return advertPlacePersistencePort.findAll();
    }

    @Override
    public AdvertPlace findById(Long id) {
        return advertPlacePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advertplace.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public AdvertPlace create(AdvertPlace advertPlace) {
        advertPlacePersistencePort.save(advertPlace);

        return advertPlace;
    }

    @Override
    public AdvertPlace update(Long id, AdvertPlace advertPlace) {
        AdvertPlace oldAdvertPlace = findById(id);

        oldAdvertPlace.setName(advertPlace.getName());
        advertPlacePersistencePort.save(oldAdvertPlace);

        return oldAdvertPlace;
    }

    @Override
    public void delete(Long id) {
        AdvertPlace advertPlace = findById(id);

        advertPlace.setIsDeleted(true);

        advertPlacePersistencePort.save(advertPlace);
    }
}
