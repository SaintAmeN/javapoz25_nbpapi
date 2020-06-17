package org.sda.nest.nbpapi.model;

import lombok.*;
import org.sda.nest.nbpapi.exception.CurrencyNotFoundException;
import org.sda.nest.nbpapi.exception.WrongDateException;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswers {
    private NBPCurrency currency; //domyślnie currency ma wartość null!
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public void setCurrency(String currency) throws CurrencyNotFoundException {
        this.currency = NBPCurrency.parse(currency);
    }

    public void setDateStart(String dateToParse) throws WrongDateException {
        this.dateStart = DateParseUtil.parseDate(dateToParse);
    }

    public void setDateEnd(String dateToParse) throws WrongDateException {
        // metoda poniżej musi sprawdzać czy data końcowa jest po dacie początkowej
        this.dateEnd = DateParseUtil.parseDate(dateToParse, dateStart);
    }
}
