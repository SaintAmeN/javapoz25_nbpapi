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
    // double = nie można przypisać null
    // Double = mogę przypisać null
    private Double mid; // srednia
    private Double ask; // kupno
    private Double bid; // sprzedaż
}
