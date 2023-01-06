
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Basket basket = new Basket(new String[]{"Хлеб","Молоко","Масло"}, new int[]{30,60,90});

//        while(true) {
//            System.out.println("Введите товар и количество или введите \"end\"");
//            Scanner scanner = new Scanner(System.in);
//            String input = scanner.nextLine();
//            if (input.equals("end")) {
//                basket.printCart();
//                break;
//            } else {
//                String[] parts = input.split(" ");
//                basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
//            }
//        }
        basket.addToCart(1,67);
        basket.addToCart(2,45);
        basket.addToCart(3,14);
        basket.saveBin(new File("Save.bin"));

        Basket basket1 = Basket.loadFromBinFile(new File("Save.bin"));
        basket1.printCart();
    }
}