import java.util.ArrayList;
import java.util.List;

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
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("Total Price: $" + getTotalPrice());
        }
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
