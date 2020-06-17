package org.sda.nest.nbpapi;

import org.sda.nest.nbpapi.exception.CurrencyNotFoundException;
import org.sda.nest.nbpapi.exception.WrongDateException;
import org.sda.nest.nbpapi.model.UserAnswers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Jeśli użytkownik ma wprowadzać dane, to powinien to robić w "klasie Main"
        // - nie przekazujemy scannera
        // - nie dokonujemy redefinicji (nie tworzymy kolejnych instancji Scannera)
        UserAnswers answers = new UserAnswers();

        do {
            try {
                System.out.println("Podaj nazwę waluty:");
                answers.setCurrency(scanner.nextLine());
            } catch (CurrencyNotFoundException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getCurrency() == null);

        do {
            try {
                System.out.println("Podaj datę początkową:");
                answers.setDateStart(scanner.nextLine());
            } catch (WrongDateException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getDateStart() == null);

        do {
            try {
                System.out.println("Podaj datę końcową:");
                answers.setDateEnd(scanner.nextLine());
            } catch (WrongDateException e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
            }
        } while (answers.getDateEnd() == null);

        System.out.println(answers);
    }
}
