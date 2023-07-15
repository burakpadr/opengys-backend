package com.padr.gys.domain.statusmanager.model;

import com.padr.gys.domain.statusmanager.constant.StatusChangeReportType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusChangeReportModel<T> {

    private StatusChangeReportType type;
    private T oldEntity;
    private T updatedEntity;
}
