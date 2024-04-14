package com.padr.gys.infra.outbound.cache.tcmb.port;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.domain.tcmb.entity.ExchangeRate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TcmbExchangeRateCachePortImpl implements TcmbExchangeRateCachePort {
    
    private final TcmbExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> saveAll(List<ExchangeRate> exchangeRate) {
        return (List<ExchangeRate>) exchangeRateRepository.saveAll(exchangeRate);
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyCode(CurrencyCode currencyCode) {
        return exchangeRateRepository.findByCurrencyCode(currencyCode);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return (List<ExchangeRate>) exchangeRateRepository.findAll();
    }

    @Override
    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }
}
