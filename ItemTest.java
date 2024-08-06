import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ItemTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        List<Item> allItems = Item.readItemsFromFile("itemstock.txt");

        // Create a Cart object
        Cart cart = new Cart();

        // Add items to the cart
        System.out.println("Adding items to the cart:");
        cart.addItem(allItems.get(0));
        cart.addItem(allItems.get(1));
        cart.displayCart();

        // Remove an item from the cart
        System.out.println("\nRemoving an item from the cart:");
        cart.removeItem(allItems.get(1));
        cart.displayCart();

        // Display the cart's total price
        System.out.println("\nTotal price of items in the cart: $" + cart.getTotalPrice());

        // Use Search functionality
        Search search = new Search(allItems);
        System.out.println("\nSearching for 'tshirt': ");
        List<Item> nameResults = search.searchByName("tshirt");
        search.displayResults(nameResults);

        System.out.println("\nSearching for items priced between $30 and $50: ");
        List<Item> priceResults = search.searchByPriceRange(30, 50);
        search.displayResults(priceResults);

        // Test updating inventory and applying sales
        System.out.println("\nUpdating inventory for item with ID 101 to 50");
        allItems.get(0).updateInventory(101, 50);
        System.out.println(allItems.get(0));

        System.out.println("\nApplying 20% sale for item with ID 102");
        allItems.get(1).applySale(102, 20);
        System.out.println(allItems.get(1));

        // Admin login (dummy implementation for testing)
        System.out.println("\nTesting admin login:");
        allItems.get(0).adminLogin("adminUser", "adminPass");

        sc.close();
    }
}
