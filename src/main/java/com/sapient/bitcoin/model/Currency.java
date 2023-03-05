package com.sapient.bitcoin.model;

import lombok.Data;

/**
 * Supported currencies
 */
@Data
public class Currency {

    private String currency;

    private String country;
}
