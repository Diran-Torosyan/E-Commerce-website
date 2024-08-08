import java.io.FileNotFoundException;
import java.util.Scanner;
 
 public class ItemTest{
    public static void main(String[]args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        Item item = new Item();
        User customer = new User();
        User admin = new User();
        Cart cart = new Cart();
        //cart.addItem();
        item.readFile(); //displays info
        //item.inventory(); //changes inventory amount based on itemid and amnt to add
        //item.onSale(); //changes the price to how much the item is on sale by i.e. if 24.00 and 50% then 12.00
        //customer.customerInfo();
        //customer.customerLogin();
        //customer.loginCheck();
        //admin.admin();
        sc.close();
    }
}
