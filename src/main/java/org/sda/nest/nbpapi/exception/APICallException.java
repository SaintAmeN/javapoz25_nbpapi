package org.sda.nest.nbpapi.exception;

import java.io.IOException;

public class APICallException extends Exception {
    public APICallException(Exception e) {
        super(e);
    }

    public APICallException(String message) {
        super(message);
    }
}
// rodzaj sygnałów
//      - white list (friend)
//      - black list (foe)
