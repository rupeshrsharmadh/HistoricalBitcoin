package com.sapient.bitcoin.controller;

import com.sapient.bitcoin.annotation.ValidCurrency;
import com.sapient.bitcoin.model.HistoricalBitcoinPrice;
import com.sapient.bitcoin.service.BitcoinService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Bitcoin controller
 */
@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/v1/historical-bitcoin-price")
public class BitcoinController {

    private final BitcoinService bitCoinService;

    /**
     * Get historical Bitcoin price for the given currency from start date to end date
     * @param currency Currency
     * @param start Start date
     * @param end End date
     * @return Response entity with historical Bitcoin price
     */
    @GetMapping
    public ResponseEntity<HistoricalBitcoinPrice> getHistoricalBitcoinPrice(@RequestParam("currency") @ValidCurrency String currency,
                                                                            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(bitCoinService.getHistoricalBitcoinPrice(currency, start, end));
    }

}
