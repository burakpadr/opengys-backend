package com.padr.gys.infra.inbound.rest.user.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResetPasswordOtpRequest {
    
    @Email
    private String email;

    @NotEmpty
    @NotBlank
    private String otp;
}
