 import java.io.FileNotFoundException;
import java.util.Scanner;
 
 public class ItemTest{
    public static void main(String[]args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        Item item = new Item();
        Tops tshirt = new Tops();
        //System.out.println("What size top would you like?");
        //tshirt.setSize(sc.nextLine());
        //System.out.printf("This shirt cost $" +"%.2f%n",tshirt.realPrice());

        item.readFile();
        sc.close();
    }
}
