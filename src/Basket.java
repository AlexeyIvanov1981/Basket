import java.io.*;

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
        basketCount = new int[productsName.length];
    }

    public void addToCart(int productNum, int amount) {
        basketCount[productNum - 1] += amount;
    }

    public void printCart() {
        int sum = 0;
        for (int i = 0; i < productsName.length; i++) {
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
            for (int count : basketCount) {
                printWriter.print(count + " ");
            }
            printWriter.println();
            for (int price : productsPrice) {
                printWriter.print(price + " ");
            }
//            printWriter.println();
//            int sumTmp = 0;
//                for (int i = 0; i < productsName.length; i++) {
//                    if (basketCount[i] != 0) {
//                        sumTmp = basketCount[i] * productsPrice[i];
//                        printWriter.print(sumTmp + " ");
//                    } else printWriter.print(" - ");
//                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Save.txt"))) {
            String[] product = bufferedReader.readLine().split(" ");
            String[] stringPrice = bufferedReader.readLine().split(" ");
            String[] stringBasketCount = bufferedReader.readLine().split(" ");

            int[] price = new int[stringPrice.length];
            for (int i = 0; i < price.length; i++) {
                price[i] = Integer.parseInt(stringPrice[i]);
            }

            int[] basketCount = new int[stringBasketCount.length];
            for (int i = 0; i < basketCount.length; i++) {
                basketCount[i] = Integer.parseInt(stringBasketCount[i]);
            }

            Basket basket = new Basket(product, price);
            basket.basketCount = basketCount;
            return basket;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveBin(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            basket = (Basket)
                    objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basket;
    }
}
