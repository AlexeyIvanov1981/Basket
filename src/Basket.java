import java.io.*;
import java.util.Arrays;

public class Basket {

    private String[] productsName;
    private int[] productsPrice;
    private int[] basketCount;

    public String[] getProductsName() {
        return productsName;
    }

    public int[] getProductsPrice() {
        return productsPrice;
    }

    public int[] getBasketCount() {
        return basketCount;
    }

    public Basket(String[] productsName, int[] productsPrice) {
        this.productsName = productsName;
        this.productsPrice = productsPrice;
        basketCount = new int[basketCount.length];
    }

    public void addToCart(int productNum, int amount){
        basketCount[productNum - 1] += amount;
    }

    public void printCard(){
        int sum = 0;
        for (int i = 0; i < productsName.length; i++){
            sum += basketCount[i] * productsPrice[i];
        }
        System.out.println("You basket is: ");

        for (int i = 0; i < productsName.length; i++) {
            System.out.println(productsName[i] + ": " + basketCount[i] + " шт по цене " + productsPrice[i] +
                    " руб/шт, сумма " + basketCount[i] * productsPrice[i] + " рублей");
        }
        System.out.println("total sum: " + sum);
    }
    public void saveTxt(File textFile) {
        try (PrintWriter printWriter = new PrintWriter(textFile)) {
            for (String product : productsName) {
                printWriter.print(product + " ");
            }
            printWriter.println();
            for (int price : productsPrice) {
                printWriter.print(price + " ");
            }
            printWriter.println();
            for (int count : basketCount) {
                printWriter.print(count + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
