package com.sapient.bitcoin.cache;

import com.sapient.bitcoin.model.HistoricalBitcoinPrice;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Bitcoin cache to hold historical response
 */
@Component
@Log4j2
public class BitcoinCache {

    private HistoricalBitcoinPrice historicalBitcoinPrice;

    private Map<String, String> currencyMap;

    /**
     * Update historical Bitcoin price cache asynchronously
     * @param historicalBitcoinPrice HistoricalBitcoinPrice
     */
    @Async
    public void updateHistoricalBitcoinPriceCache(HistoricalBitcoinPrice historicalBitcoinPrice){
        log.info("Update historical Bitcoin price in cache.");
        this.historicalBitcoinPrice = historicalBitcoinPrice;
    }

    /**
     * Get historical Bitcoin price from cache
     * @return HistoricalBitcoinPrice
     */
    public HistoricalBitcoinPrice getHistoricalBitcoinPriceFromCache(){
        log.info("Getting historical Bitcoin price from cache, found {}", historicalBitcoinPrice);
        return historicalBitcoinPrice;
    }

    /**
     * Update supported currencies map cache asynchronously
     * @param currencyMap Map of supported currencies
     */
    @Async
    public void updateSupportedCurrenciesCache(Map<String, String> currencyMap){
        log.info("Update supported currencies in cache.");
        this.currencyMap = currencyMap;
    }

    /**
     * Get supported currencies cache
     * @return Supported currencies
     */
    public Map<String, String> getSupportedCurrenciesFromCache(){
        log.info("Getting supported currencies from cache, found {}", currencyMap != null);
        return currencyMap;
    }
}
