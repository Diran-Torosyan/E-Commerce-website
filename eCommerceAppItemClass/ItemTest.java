import java.util.Scanner;

public class ItemTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Item tshirt = new Item();
        System.out.print("What size top would you like? ");
        tshirt.setSize(sc.nextLine());
        System.out.println("This shirt costs: $" + tshirt.realPrice());
        sc.close();
    }
}
