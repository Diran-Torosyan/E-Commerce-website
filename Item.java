import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Item {
    private String name;
    private double price;
    private String color;
    private String size;
    private double salePrice;
    private int invAmnt;
    private boolean sale;
    private boolean inStock;
    private int itemId;

    public Item(String name, double price, String color, String size, double salePrice, int invAmnt, boolean sale, boolean inStock, int itemId) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.salePrice = salePrice;
        this.invAmnt = invAmnt;
        this.sale = sale;
        this.inStock = inStock;
        this.itemId = itemId;
    }

    public Item() {}

    // Getters and Setters...

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    public String getSize() { return size; }
    public double getSalePrice() { return salePrice; }
    public int getInvAmnt() { return invAmnt; }
    public boolean isSale() { return sale; }
    public boolean isInStock() { return inStock; }
    public int getItemId() { return itemId; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setColor(String color) { this.color = color; }
    public void setSize(String size) { this.size = size; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }
    public void setInvAmnt(int invAmnt) { this.invAmnt = invAmnt; }
    public void setSale(boolean sale) { this.sale = sale; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    @Override
    public String toString() {
        return itemId + " " + name + " " + size + " $" + price + " (" + invAmnt + " in stock)";
    }

    // Method to read items from a file
    public static List<Item> readItemsFromFile(String fileName) throws FileNotFoundException {
        List<Item> items = new ArrayList<>();
        Scanner filesc = new Scanner(new File(fileName));
        while (filesc.hasNextLine()) {
            String data = filesc.nextLine();
            String[] a = data.split(" ");
            Item item = new Item(a[1], Double.parseDouble(a[3]), "colorPlaceholder", a[2], 0.0, Integer.parseInt(a[4]), false, true, Integer.parseInt(a[0]));
            items.add(item);
        }
        filesc.close();
        return items;
    }

    // Inventory method
    public void updateInventory(int itemId, int newAmount) {
        if (this.itemId == itemId) {
            this.invAmnt = newAmount;
        }
    }

    // On sale method
    public void applySale(int itemId, double salePercentage) {
        if (this.itemId == itemId) {
            this.salePrice = this.price - (this.price * salePercentage / 100);
            this.sale = true;
        }
    }

    // Admin method
    public void adminLogin(String username, String password) throws FileNotFoundException {
        File login = new File("admin.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(login))) {
            String user = br.readLine();
            String pass = br.readLine();
            if (username.equals(user) && password.equals(pass)) {
                System.out.println("Admin login successful.");
            } else {
                System.out.println("Incorrect login info provided.");
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
