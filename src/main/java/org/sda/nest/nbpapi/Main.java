package org.sda.nest.nbpapi;

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
                e.printStackTrace();
            }
        }while (answers.getCurrency() == null);


        System.out.println("Podaj datę początkową:");

        System.out.println("Podaj datę końcową:");
    }
}
