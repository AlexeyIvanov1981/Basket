package ru.home;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Basket {
    private final String[] productsName;
    private final int[] productsPrice;
    private int[] productCountInBasket;

    public String[] getProductsName() {
        return productsName;
    }

    public int[] getProductsPrice() {
        return productsPrice;
    }

    public int[] getProductCountInBasket() {
        return productCountInBasket;
    }

    public Basket(String[] productsName, int[] productsPrice) {
        this.productsName = productsName;
        this.productsPrice = productsPrice;
        this.productCountInBasket = new int[productsName.length];
    }

    public void addToCart(int productNum, int amount) {
        this.productCountInBasket[productNum - 1] += amount;
    }

    public void printCart() {
        int sum = 0;
        for (int i = 0; i < productsName.length; i++) {
            sum += productCountInBasket[i] * productsPrice[i];
        }
        System.out.println("You basket is: ");

        for (int i = 0; i < productsName.length; i++) {
            System.out.println(productsName[i] + ": " + productCountInBasket[i] + " шт по цене " + productsPrice[i] +
                    " руб/шт, сумма " + productCountInBasket[i] * productsPrice[i] + " рублей");
        }
        System.out.println("total sum: " + sum);
    }

    public void saveBasketToTextFile(File textFile) {
        try (PrintWriter printWriter = new PrintWriter(textFile)) {
            for (String product : productsName) {
                printWriter.print(product + " ");
            }
            printWriter.println();
            for (int count : productCountInBasket) {
                printWriter.print(count + " ");
            }
            printWriter.println();
            for (int price : productsPrice) {
                printWriter.print(price + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String[] product = bufferedReader.readLine().split(" ");
            String[] stringBasketCount = bufferedReader.readLine().split(" ");
            String[] stringPrice = bufferedReader.readLine().split(" ");

            int[] price = new int[stringPrice.length];
            for (int i = 0; i < price.length; i++) {
                price[i] = Integer.parseInt(stringPrice[i]);
            }

            int[] basketCount = new int[stringBasketCount.length];
            for (int i = 0; i < basketCount.length; i++) {
                basketCount[i] = Integer.parseInt(stringBasketCount[i]);
            }

            Basket basket = new Basket(product, price);
            basket.productCountInBasket = basketCount;
            return basket;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Basket loadBasketFromJsonFile(File jsonFile) {
        try (FileReader fileReader = new FileReader(jsonFile)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(fileReader, Basket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBasketToJsonFile(File jsonFile) {
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            gson.toJson(this, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
