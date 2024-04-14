package com.padr.gys.infra.outbound.client.tcmb.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Tarih_Date")
public class TcmbExchangeRateResponse {

    @JacksonXmlProperty(localName = "Date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;

    @JacksonXmlProperty(localName = "Currency")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Currency> currencies;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {

        @JacksonXmlProperty(localName = "CurrencyCode")
        private String currencyCode;

        @JacksonXmlProperty(localName = "Unit")
        private Integer unit;

        @JacksonXmlProperty(localName = "Isim")
        private String name;

        @JacksonXmlProperty(localName = "CurrencyName")
        private String currencyName;

        @JacksonXmlProperty(localName = "ForexBuying")
        private BigDecimal forexBuying;

        @JacksonXmlProperty(localName = "ForexSelling")
        private BigDecimal forexSelling;

        @JacksonXmlProperty(localName = "BanknoteBuying")
        private BigDecimal BanknoteBuying;

        @JacksonXmlProperty(localName = "BanknoteSelling")
        private BigDecimal BanknoteSelling;
    }
}
