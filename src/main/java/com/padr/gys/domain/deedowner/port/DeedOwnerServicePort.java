package com.padr.gys.domain.deedowner.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.deedowner.entity.DeedOwner;

public interface DeedOwnerServicePort {
    
    Page<DeedOwner> findAll(Pageable pageable);

    DeedOwner findById(Long id);

    DeedOwner create(DeedOwner deedOwner);

    DeedOwner update(DeedOwner olDeedOwner, DeedOwner newDeedOwner);

    void delete(DeedOwner deedOwner);
}
