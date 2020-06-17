package org.sda.nest.nbpapi.exception;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String message) {
        super("Currency " + message + " could not be found!");
    }
}
