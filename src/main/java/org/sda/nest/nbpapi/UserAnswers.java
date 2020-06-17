package org.sda.nest.nbpapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswers {
    private NBPCurrency currency; //domyślnie currency ma wartość null!
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    public void setCurrency(String currency) throws CurrencyNotFoundException {
        this.currency = NBPCurrency.parse(currency);
    }
}
