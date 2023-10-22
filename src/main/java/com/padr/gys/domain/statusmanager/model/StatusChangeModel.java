package com.padr.gys.domain.statusmanager.model;

import com.padr.gys.domain.statusmanager.constant.StatusChangeOperationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusChangeModel {

    private StatusChangeOperationType type;
    private Object oldEntity;
    private Object updatedEntity;
}
