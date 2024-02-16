package com.padr.gys.infra.inbound.rest.user.model.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResetPasswordOtpRequest {
    
    @Email
    private String email;
}
