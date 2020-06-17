package org.sda.nest.nbpapi;

import org.sda.nest.nbpapi.exception.APICallException;
import org.sda.nest.nbpapi.exception.CurrencyNotFoundException;
import org.sda.nest.nbpapi.exception.WrongDateException;
import org.sda.nest.nbpapi.model.CurrencyRate;
import org.sda.nest.nbpapi.model.UserAnswers;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Jeśli użytkownik ma wprowadzać dane, to powinien to robić w "klasie Main"
        // - nie przekazujemy scannera
        // - nie dokonujemy redefinicji (nie tworzymy kolejnych instancji Scannera)
        UserAnswers answers = new UserAnswers();

        askForCurrency(scanner, answers);
        askForDateStart(scanner, answers);
        askForDateEnd(scanner, answers);

        System.out.println(answers);

        NBPApi api = new NBPApi();
        try {
            List<CurrencyRate> rates = api.requestRates(answers);

            rates.forEach(rate -> {
                System.out.println(rate.getNo() + " -- " + rate.getEffectiveDate() + " -- " + rate.getMid());
            });
            // jeśli wszystko jest ok!
        } catch (APICallException e) {
            System.err.println(e.getMessage());
            System.err.println("Nie mogłem dokończyć pracy. :(");
        }
    }

    private static void askForDateEnd(Scanner scanner, UserAnswers answers) {
        do {
            try {
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
