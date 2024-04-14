package com.padr.gys.domain.tcmb.port;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.domain.tcmb.entity.ExchangeRate;
import com.padr.gys.infra.outbound.cache.tcmb.port.TcmbExchangeRateCachePort;
import com.padr.gys.infra.outbound.client.tcmb.constant.TcmbEndpoint;
import com.padr.gys.infra.outbound.client.tcmb.model.response.TcmbExchangeRateResponse;
import com.padr.gys.infra.outbound.client.tcmb.port.TcmbClientPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class TcmbServicePortImpl implements TcmbServicePort {

    private final TcmbClientPort tcmbClientPort;
    private final TcmbExchangeRateCachePort tcmbExchangeRateCachePort;

    @Override
    public TcmbExchangeRateResponse getTodayExchangeRates() {
        return tcmbClientPort.send(TcmbEndpoint.KURLAR_TODAY.getEndpoint(),
                HttpMethod.GET,
                null,
                new TypeReference<TcmbExchangeRateResponse>() {
                },
                null)
                .getBody();
    }

    @Override
    public TcmbExchangeRateResponse getArchivedExchangeRates(LocalDate archiveDate) {
        String day = archiveDate.format(DateTimeFormatter.ofPattern("dd"));
        String month = archiveDate.format(DateTimeFormatter.ofPattern("MM"));

        String endpoint = "/" + archiveDate.getYear() + month + "/" + day + month + archiveDate.getYear() + ".xml";

        return tcmbClientPort.send(endpoint,
                HttpMethod.GET,
                null,
                new TypeReference<TcmbExchangeRateResponse>() {
                },
                null)
                .getBody();
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyCode(CurrencyCode currencyCode) {
        return tcmbExchangeRateCachePort.findByCurrencyCode(currencyCode);
    }

    @Override
    public List<ExchangeRate> createAll(List<ExchangeRate> exchangeRate) {
        return tcmbExchangeRateCachePort.saveAll(exchangeRate);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return tcmbExchangeRateCachePort.findAll();
    }

    @Override
    public void deleteAll() {
        tcmbExchangeRateCachePort.deleteAll();
    }
}
