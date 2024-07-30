import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Item{
    
    private String name;
    private double price;
    private String color;
    //item ID and color may be added later


    public Item (String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }
    
    //getters
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return name + ": $" + price;
    }
}

public class Cart {
    private List<Item> items;

    //constructor
    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    public void displayCart() {
        System.out.println("Shopping Cart: ");
        if(items.isEmpty()) {
            System.out.println("The cart is empty. ");
        }
        else{
            //for-each loop to go through items list per object item of type Item
            for(Item item : items) {
                //print item name
                System.out.println(item);
            }
            System.out.println("Total Price: $" + getTotalPrice());
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for(Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

 /*   public static class Item {
        private String name;
        private double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
*/
public static class Search{

    private List<Item> items;

    //constructor
    public Search(List<Item> items) {
        this.items = items;
    }

    //search by name
    public List<Item> searchByName(String name) {
        return items.stream().filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }

    //search by color (case-insensitive)
    public List<Item> searchByColor(String color) {
        return items.stream().filter(item -> item.getColor().toLowerCase().contains(color.toLowerCase()))
            .collect(Collectors.toList());
    }

    //search by price range
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        return items.stream().filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
            .collect(Collectors.toList());
    }

    //display results
    public void displayResults(List<Item> results) {
        if(results.isEmpty()) {
            System.out.println("No items found.");
        }
        else {
            results.forEach(System.out::println);
        }
    }
}
    public static void main(String[] args) {
        Cart cart = new Cart();
        
        Item item1 = new Item("phone", 1599.99, "silver");
        Item item2 = new Item("tv", 999.99, "black");
        Item item3 = new Item("pc", 4999.99, "white");
        
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        
        cart.displayCart();

        cart.removeItem(item2);

        System.out.println("Display cart after removing tv");
        cart.displayCart();
        
        Search search = new Search(cart.getItems());

        System.out.println("Searching for 'silver': ");
        List<Item> colorResults = search.searchByColor("silver");
        search.displayResults(colorResults);
    }
}
