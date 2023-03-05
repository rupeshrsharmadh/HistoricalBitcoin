package com.sapient.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class HistoricalBitcoinPrice {

    private Map<String, BigDecimal> bpi;

    private Map.Entry<String, BigDecimal> maximumRate;

    private Map.Entry<String, BigDecimal> minimumRate;
}
