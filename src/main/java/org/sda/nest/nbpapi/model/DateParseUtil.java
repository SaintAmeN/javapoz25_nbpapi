package org.sda.nest.nbpapi.model;

import org.sda.nest.nbpapi.exception.WrongDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateParseUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Przetworzenie daty i porównanie do startDate.
     * @param input - data do przetworzenia, jest porównywana z startDate
     * @param startDate - data która !musi być! wcześniejsza niż data podana w input.
     * @return przetworzona data.
     * @throws WrongDateException - rzuca błąd daty, jeśli data jest w złym formacie lub jest przed datą startową.
     */
    public static LocalDate parseDate(String input, LocalDate startDate) throws WrongDateException {
        LocalDate date;
        try {
            date = LocalDate.parse(input, dateTimeFormatter);
            if (startDate.isAfter(date)) { // jeśli data jest późniejsza niż startDate
                throw new WrongDateException("Date should not be after start date!");
            }

        } catch (DateTimeParseException dtpe) {
            throw new WrongDateException("Wrong date format");
        }
        return date;
    }

    /**
     *
     * @param input
     * @return przetworzona data.
     * @throws WrongDateException - rzuca błąd daty, jeśli data jest w złym formacie lub jest przed datą startową.
     */
    public static LocalDate parseDate(String input) throws WrongDateException {
        LocalDate date;
        try {
            date = LocalDate.parse(input, dateTimeFormatter);
            if (!LocalDate.now().isAfter(date)) { // jeśli data jest późniejsza niż startDate
                throw new WrongDateException("Date should not be after start date!");
            }

        } catch (DateTimeParseException dtpe) {
            throw new WrongDateException("Wrong date format");
        }
        return date;
    }
}
