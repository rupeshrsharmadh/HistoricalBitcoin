package com.sapient.bitcoin.dao;

import com.sapient.bitcoin.model.Currency;
import com.sapient.bitcoin.model.HistoricalBitcoinPrice;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * Bitcoin Data layer
 */
@Service
@RequiredArgsConstructor
public class BitcoinDAO {

    // Rest template to make rest api calls
    private final RestTemplate restTemplate;

    // BPI historical price url
    @Value("${bpi.historical.url}")
    private String bpiHistoricalUrl;

    // BPI supported currencies url
    @Value("${bpi.currencies.url}")
    private String bpiCurrenciesUrl;

    /**
     * Get Historical price
     * @param currency Currency
     * @param start Start date
     * @param end End date
     * @return Historical Bitcoin price
     */
    public HistoricalBitcoinPrice getHistoricalPriceResponse(String currency, LocalDate start, LocalDate end) {
        return restTemplate.getForObject(bpiHistoricalUrl+ "?start="+start.toString()+"&end="+end.toString()+"&currency=" + currency, HistoricalBitcoinPrice.class);
    }

    /**
     * Get supported currencies
     * @return List of supported currencies
     */
    public List<Currency> getSupportedCurrencies() {
        return restTemplate.exchange(bpiCurrenciesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Currency>>(){}).getBody();
    }

}
