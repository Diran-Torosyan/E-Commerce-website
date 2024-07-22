 import java.util.Scanner;
 
 public class ItemTest{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Item tshirt = new Item();
        tshirt.getPrice();
        System.out.println("What size top would you like?");
        sc.nextLine();
        System.out.println("This shirt cost: " + tshirt.realPrice());
    }
}
