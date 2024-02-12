package com.padr.gys.infra.inbound.rest.user.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateResetPasswordOtpResponse {
    
    private Boolean isMatched;
}
