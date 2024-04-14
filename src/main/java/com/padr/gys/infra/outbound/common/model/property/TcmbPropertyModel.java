package com.padr.gys.infra.outbound.common.model.property;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TcmbPropertyModel {

    private String protocol;
    private String domainName;
    private String kurlarContextPath;

    public String getKurlarBaseUrl() {
        return String.format("%s://%s/%s", protocol, domainName, kurlarContextPath);
    }
}
