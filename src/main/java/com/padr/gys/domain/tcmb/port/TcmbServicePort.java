package com.padr.gys.domain.tcmb.port;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.domain.tcmb.entity.ExchangeRate;
import com.padr.gys.infra.outbound.client.tcmb.model.response.TcmbExchangeRateResponse;

public interface TcmbServicePort {
    
    TcmbExchangeRateResponse getTodayExchangeRates();

    TcmbExchangeRateResponse getArchivedExchangeRates(LocalDate archiveDate);

    Optional<ExchangeRate> findByCurrencyCode(CurrencyCode currencyCode);

    List<ExchangeRate> createAll(List<ExchangeRate> exchangeRate);

    List<ExchangeRate> findAll();

    void deleteAll();
}
