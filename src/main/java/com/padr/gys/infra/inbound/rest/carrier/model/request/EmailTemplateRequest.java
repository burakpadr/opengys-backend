package com.padr.gys.infra.inbound.rest.carrier.model.request;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateRequest {
    
    @NotEmpty
    private EmailTemplateCode code;

    @NotEmpty
    private String label;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;
}
