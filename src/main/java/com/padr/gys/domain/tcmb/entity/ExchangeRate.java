package com.padr.gys.domain.tcmb.entity;

import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.infra.outbound.client.tcmb.model.response.TcmbExchangeRateResponse.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("ExchangeRate")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    
    private String id;

    @Indexed
    private CurrencyCode currencyCode;

    private Integer unit;
    private BigDecimal forexBuying;
    private BigDecimal forexSelling;

    public static ExchangeRate of(Currency currency) {
        return ExchangeRate.builder()
                .currencyCode(CurrencyCode.valueOf(currency.getCurrencyCode()))
                .unit(currency.getUnit())
                .forexBuying(currency.getForexBuying())
                .forexSelling(currency.getForexSelling())
                .build();
    }
}
