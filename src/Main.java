import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Basket basket = new Basket(new String[]{"Хлеб","Молоко","Масло"}, new int[]{30,60,90});

        while(true) {
            System.out.println("Введите товар и количество или введите 'end'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basket.printCart();
                break;
            } else {
                String[] parts = input.split(" ");
                basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }
        }


//        basket.addToCart(1,10);
//        basket.addToCart(2,20);
//        basket.addToCart(3,30);
//        basket.addToCart(2,5);

        basket.saveTxt(new File("Save.txt"));
        Basket basketToLoad = Basket.loadFromTxtFile(new File("Save.txt"));
//        basketToLoad.printCart();
    }
}