package com.padr.gys.infra.inbound.carrier.model.response;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailTemplateResponse {

    private Long id;
    private EmailTemplateCode code;
    private String label;
    private String content;

    public static EmailTemplateResponse of(EmailTemplate emailTemplate) {
        return EmailTemplateResponse.builder()
                .id(emailTemplate.getId())
                .code(emailTemplate.getCode())
                .label(emailTemplate.getLabel())
                .content(emailTemplate.getContent())
                .build();
    }
}
