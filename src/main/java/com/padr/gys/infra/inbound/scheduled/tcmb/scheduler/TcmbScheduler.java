package com.padr.gys.infra.inbound.scheduled.tcmb.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.tcmb.entity.ExchangeRate;
import com.padr.gys.domain.tcmb.port.TcmbServicePort;
import com.padr.gys.infra.outbound.client.tcmb.model.response.TcmbExchangeRateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TcmbScheduler {

    private final TcmbServicePort tcmbServicePort;

    // This scheduled job works every 1 hour
    
    @Scheduled(fixedRate = 3600000)
    public void saveTodayExchangeRatesToCache() { 
        TcmbExchangeRateResponse exchangeRatesFromTcmb = tcmbServicePort.getTodayExchangeRates();
        List<ExchangeRate> exchangeRatesFromCache = tcmbServicePort.findAll();

        if (exchangeRatesFromCache.size() < 0 || !(LocalDate.now().equals(exchangeRatesFromTcmb.getDate()))) {
            tcmbServicePort.deleteAll();

            List<ExchangeRate> exchangeRates = exchangeRatesFromTcmb.getCurrencies()
                    .stream()
                    .map(ExchangeRate::of)
                    .toList();

            tcmbServicePort.createAll(exchangeRates);
        }
    }
}
