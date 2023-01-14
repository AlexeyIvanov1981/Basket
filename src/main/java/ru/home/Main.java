package ru.home;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String EXPORT_LOG_FILENAME = "log.csv";
    private static final String BASKET_FILENAME_TXT = "basket.txt";
    private static final String BASKET_FILENAME_JSON = "basket.json";

    public static void main(String[] args) {
        Basket basket = Basket.loadBasketFromJsonFile(new File(BASKET_FILENAME_JSON));

        ClientLog clientLog = new ClientLog();

        while (true) {
            System.out.println("Введите товар и количество или введите 'end'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basket.printCart();
                break;
            } else {
                String[] parts = input.split(" ");
                final int productNum = Integer.parseInt(parts[0]);
                final int productAmount = Integer.parseInt(parts[1]);

                basket.addToCart(productNum, productAmount);
                clientLog.log(productNum, productAmount);
            }
        }

        basket.saveBasketToJsonFile(basket, new File(BASKET_FILENAME_JSON));
        clientLog.exportAsCSV(new File(EXPORT_LOG_FILENAME));
    }
}