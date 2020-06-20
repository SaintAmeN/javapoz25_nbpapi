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
    private TableType tableType;

    public void setCurrency(String currency) throws CurrencyNotFoundException {
        this.currency = NBPCurrency.parse(currency);
    }

    /**
     * Przetwarzanie daty startowej. Data startowa porównywana jest do obecnej daty.
     * @param dateToParse - data w formacie yyyy-MM-dd, podana przed obecną datą
     * @throws WrongDateException - rzuca błąd daty, jeśli data jest w złym formacie lub jest przed datą startową.
     */
    public void setDateStart(String dateToParse) throws WrongDateException {
        this.dateStart = DateParseUtil.parseDate(dateToParse);
    }

    /**
     * Ustawiamy datę końcową zakresu - dla daty wpisanej w parametr dateToParse upewniamy się, że jest po dacie startowej
     * z pola dateStart z tej klasy.
     * @param dateToParse - wejście, data w formacie yyyy-MM-dd
     * @throws WrongDateException - rzuca błąd daty, jeśli data jest w złym formacie lub jest przed datą startową.
     */
    public void setDateEnd(String dateToParse) throws WrongDateException {
        // metoda poniżej musi sprawdzać czy data końcowa jest po dacie początkowej
        this.dateEnd = DateParseUtil.parseDate(dateToParse, dateStart);
    }

    public void setTableType(String response) {
        if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("tak") || response.equalsIgnoreCase("yes")) {
            this.tableType = TableType.TABLE_MID;
        }else{
            this.tableType = TableType.TABLE_BID_ASK;
        }
    }
}
