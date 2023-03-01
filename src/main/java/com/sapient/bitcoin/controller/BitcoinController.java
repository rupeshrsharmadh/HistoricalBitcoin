package com.sapient.bitcoin.controller;

import com.sapient.bitcoin.model.HistoricalBitCoinPriceResponse;
import com.sapient.bitcoin.service.BitCoinService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/historical-bitcoin-price")
public class BitcoinController {

    private final BitCoinService bitCoinService;

    @GetMapping
    public ResponseEntity<HistoricalBitCoinPriceResponse> getHistoricalBitcoinPrice(@RequestParam("currency") String currency,
                                                                                    @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                                    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) throws IOException, InterruptedException {
        return ResponseEntity.ok(bitCoinService.getHistoricalBitcoinPrice(currency, start, end));
    }

}
