package com.sapient.bitcoin.service;

import com.sapient.bitcoin.dao.BitCoinDAO;
import com.sapient.bitcoin.model.HistoricalBitCoinPriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;


@Service
@AllArgsConstructor
public class BitCoinService {
    private final BitCoinDAO apiService;

    public HistoricalBitCoinPriceResponse getHistoricalBitcoinPrice(String currency, LocalDate start, LocalDate end) throws IOException, InterruptedException {
      HistoricalBitCoinPriceResponse response = apiService.getHistoricalPriceResponse(currency, start, end);
      getMaximumRate(response);
      getMaximumRate(response);
      return response;
    }
    public void getMaximumRate(HistoricalBitCoinPriceResponse response) {
        response.setMaximumRate(Collections.max(response.getBpi().entrySet(), Map.Entry.comparingByValue()));
    }

    public  void getMinimumRate(HistoricalBitCoinPriceResponse response) {
        response.setMinimumRate(Collections.min(response.getBpi().entrySet(), Map.Entry.comparingByValue()));
    }


}