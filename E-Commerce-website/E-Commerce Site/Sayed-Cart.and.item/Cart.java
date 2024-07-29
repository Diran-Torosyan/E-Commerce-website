import java.util.ArrayList;
import java.util.List;

class Item{
    
    private String name;
    private double price;
    
    public Item (String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return name + ": $" + price;
    }
}

public class Cart {
    private List<Item> items;

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
            for(Item item : items) {
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
    public static void main(String[] args) {
        Cart cart = new Cart();
        
        Item item1 = new Item("phone", 1599.99);
        Item item2 = new Item("tv", 999.99);
        Item item3 = new Item("pc", 4999.99);
        
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        
        cart.displayCart();
        
    }
}
