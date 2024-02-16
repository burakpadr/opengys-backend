package com.padr.gys.infra.inbound.rest.user.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetUserPasswordOtpRequest {
    
    @Email
    private String email;

    @NotEmpty
    private String password;
}
