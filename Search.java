import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private List<Item> items;

    public Search(List<Item> items) {
        this.items = items;
    }

    public List<Item> searchByName(String name) {
        return items.stream().filter(item -> item.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> searchByColor(String color) {
        return items.stream().filter(item -> item.getColor().toLowerCase().contains(color.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        return items.stream().filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    public void displayResults(List<Item> results) {
        if (results.isEmpty()) {
            System.out.println("No items found.");
        } else {
            results.forEach(System.out::println);
        }
    }
}
