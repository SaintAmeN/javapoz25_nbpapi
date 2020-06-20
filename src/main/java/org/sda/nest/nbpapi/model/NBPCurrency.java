package org.sda.nest.nbpapi.model;

import org.sda.nest.nbpapi.exception.CurrencyNotFoundException;

public enum NBPCurrency {
    RUBEL("RUB"),
    DOLAR("USD"),
    EURO("EUR");
    private String shortName;

    NBPCurrency(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    /**
     * Parser waluty -szukamy dopasowania po jednym ze słow: rubel, dolar, euro
     * @param input
     * @return
     * @throws CurrencyNotFoundException
     */
    public static NBPCurrency parse(String input) throws CurrencyNotFoundException {
        if (input.toUpperCase().contains(RUBEL.toString())) {
            return RUBEL;
        } else if (input.toUpperCase().contains(DOLAR.toString())) {
            return DOLAR;
        } else if (input.toUpperCase().contains(EURO.toString())) {
            return EURO;
        }
        throw new CurrencyNotFoundException(input);
    }
}
