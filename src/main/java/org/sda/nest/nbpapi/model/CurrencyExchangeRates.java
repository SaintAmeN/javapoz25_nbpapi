package org.sda.nest.nbpapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeRates {
    private String table;
    private String currency;
    private String code;
    private List<CurrencyRate> rates;
}
