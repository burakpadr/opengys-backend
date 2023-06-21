package com.abctech.gys.infra.inbound.common.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    
    private String code;
    private String message;
}
