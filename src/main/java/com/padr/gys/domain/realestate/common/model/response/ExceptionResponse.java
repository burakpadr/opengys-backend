package com.padr.gys.domain.realestate.common.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    
    private String code;
    private String message;
}
