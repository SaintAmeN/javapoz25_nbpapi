package org.sda.nest.nbpapi;

import org.sda.nest.nbpapi.exception.APICallException;
import org.sda.nest.nbpapi.exception.CurrencyNotFoundException;
import org.sda.nest.nbpapi.exception.WrongDateException;
import org.sda.nest.nbpapi.model.CurrencyRate;
import org.sda.nest.nbpapi.model.TableType;
import org.sda.nest.nbpapi.model.UserAnswers;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Jeśli użytkownik ma wprowadzać dane, to powinien to robić w "klasie Main"
        // - nie przekazujemy scannera
        // - nie dokonujemy redefinicji (nie tworzymy kolejnych instancji Scannera)

        // zbieramy odpowiedzi do konetenera (klasy zbiorczej) - UserAnswers
        UserAnswers answers = new UserAnswers();

        askForCurrency(scanner, answers);
        askForDateStart(scanner, answers);
        askForDateEnd(scanner, answers);

        System.out.println(answers);

        NBPApi api = new NBPApi();
        try {
            System.out.println("Czy chcesz tabelę kursów średnich:");
            answers.setTableType(scanner.nextLine());

            List<CurrencyRate> rates = api.requestRates(answers);

            rates.forEach(rate -> {
                // pętla foreach która wypisuje kolejno wszystkie rekordy
                System.out.println(rate.getNo() + " -- " + rate.getEffectiveDate() + " -- " + rate.getMid() + " -- " + rate.getAsk() + " -- " + rate.getBid());
            });

            if(answers.getTableType() == TableType.TABLE_BID_ASK){
                Optional<CurrencyRate> rateMin = rates.stream().min((o1, o2) -> Double.compare(o1.getAsk(), o2.getAsk()));
                if(rateMin.isPresent()){
                    System.out.println("Min: " + rateMin.get());
                }
                Optional<CurrencyRate> rateMax = rates.stream().max((o1, o2) -> Double.compare(o1.getBid(), o2.getBid()));
                if(rateMax.isPresent()){
                    System.out.println("Max: " + rateMax.get());
                }
            }

            // jeśli wszystko jest ok!
        } catch (APICallException e) {
            System.err.println(e.getMessage());
            System.err.println("Nie mogłem dokończyć pracy. :(");
        }
    }

    private static void askForDateEnd(Scanner scanner, UserAnswers answers) {
        // do - while = wiem że jeden obieg musi się wykonać
        do {
            try {
                // pytanko o datę i zebranie odpowiedzi
                System.out.println("Podaj datę końcową:");
                answers.setDateEnd(scanner.nextLine());
            } catch (WrongDateException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getDateEnd() == null);
    }

    private static void askForDateStart(Scanner scanner, UserAnswers answers) {
        do {
            try {
                System.out.println("Podaj datę początkową:");
                answers.setDateStart(scanner.nextLine());
            } catch (WrongDateException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getDateStart() == null);
    }

    private static void askForCurrency(Scanner scanner, UserAnswers answers) {
        do {
            try {
                System.out.println("Podaj nazwę waluty:");
                answers.setCurrency(scanner.nextLine());
            } catch (CurrencyNotFoundException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getCurrency() == null);
    }
}
