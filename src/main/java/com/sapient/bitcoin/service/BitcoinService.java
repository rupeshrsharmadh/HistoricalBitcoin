package com.sapient.bitcoin.service;

import com.sapient.bitcoin.cache.BitcoinCache;
import com.sapient.bitcoin.dao.BitcoinDAO;
import com.sapient.bitcoin.model.Currency;
import com.sapient.bitcoin.model.HistoricalBitcoinPrice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bitcoin service
 */

@Service
@AllArgsConstructor
@Log4j2
public class BitcoinService {
    private final BitcoinDAO bitcoinDAO;

    private final BitcoinCache bitcoinCache;

    /**
     * Get historical Bitcoin price for the given currency from start date to end date
     * @param currency Currency
     * @param start Start date
     * @param end End date
     * @return Historical Bitcoin response {@link HistoricalBitcoinPrice}
     */
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "getHistoricalBitcoinPriceFromCache")
    public HistoricalBitcoinPrice getHistoricalBitcoinPrice(String currency, LocalDate start, LocalDate end) {

        log.info("Get historical Bitcoin price for currency {} from {} to end {}", currency, start, end);

        HistoricalBitcoinPrice response = bitcoinDAO.getHistoricalPriceResponse(currency, start, end);

        // Get maximum rate
        getMaximumRate(response);

        // Get minimum rate
        getMinimumRate(response);

        // Update the response in cache asynchronously
        bitcoinCache.updateHistoricalBitcoinPriceCache(response);

        return response;
    }

    private void getMaximumRate(HistoricalBitcoinPrice response) {
        response.setMaximumRate(Collections.max(response.getBpi().entrySet(), Map.Entry.comparingByValue()));
    }

    private  void getMinimumRate(HistoricalBitcoinPrice response) {
        response.setMinimumRate(Collections.min(response.getBpi().entrySet(), Map.Entry.comparingByValue()));
    }

    /**
     * Get historical Bitcoin response from cache
     * @param ex Exception
     * @return historical Bitcoin response
     */
    public HistoricalBitcoinPrice getHistoricalBitcoinPriceFromCache(Exception ex) {

        log.error("Exception while getting historical Bitcoin price {}", ex);

        log.info("Getting historical Bitcoin response from cache.");

        return bitcoinCache.getHistoricalBitcoinPriceFromCache();
    }

    /**
     * Get supported currencies
     * @return Map of supported currencies
     */
    public Map<String, String> getSupportedCurrencies(){
        Map<String, String> currencyMap = bitcoinCache.getSupportedCurrenciesFromCache();
        if(currencyMap == null){
            currencyMap  = bitcoinDAO.getSupportedCurrencies().stream()
                    .collect(Collectors.toMap(Currency::getCurrency, Currency::getCountry));
            bitcoinCache.updateSupportedCurrenciesCache(currencyMap);
        }

        return currencyMap;
    }

}