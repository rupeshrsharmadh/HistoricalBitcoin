package com.sapient.bitcoin.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.bitcoin.model.HistoricalBitCoinPriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BitCoinDAO {

    private final ObjectMapper objectMapper;

    private final static String HISTORICAL_BPI_URL ="https://api.coindesk.com/v1/bpi/historical/close";

    public HistoricalBitCoinPriceResponse getHistoricalPriceResponse(String currency, LocalDate start, LocalDate end) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(HISTORICAL_BPI_URL+ "?start="+start.toString()+"&end="+end.toString()+"&currency=" + currency))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), HistoricalBitCoinPriceResponse.class);
    }

}
