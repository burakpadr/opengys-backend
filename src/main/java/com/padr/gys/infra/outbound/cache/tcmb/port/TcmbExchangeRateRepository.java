package com.padr.gys.infra.outbound.cache.tcmb.port;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.tcmb.constant.CurrencyCode;
import com.padr.gys.domain.tcmb.entity.ExchangeRate;

@Repository
interface TcmbExchangeRateRepository extends CrudRepository<ExchangeRate, String> {
    
    Optional<ExchangeRate> findByCurrencyCode(CurrencyCode currencyCode); 
}
