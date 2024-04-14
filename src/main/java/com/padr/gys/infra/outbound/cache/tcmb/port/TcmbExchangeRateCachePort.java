package com.padr.gys.infra.outbound.cache.tcmb.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.domain.tcmb.entity.ExchangeRate;

public interface TcmbExchangeRateCachePort {
    
    List<ExchangeRate> saveAll(List<ExchangeRate> exchangeRate);

    Optional<ExchangeRate> findByCurrencyCode(CurrencyCode currencyCode);

    List<ExchangeRate> findAll();

    void deleteAll();
}
