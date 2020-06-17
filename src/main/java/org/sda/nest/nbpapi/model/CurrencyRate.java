package org.sda.nest.nbpapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {
    private String no;
    private String effectiveDate;
    private double mid;
}
