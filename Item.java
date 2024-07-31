import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class Item {

    // instance variables

    // string size = read from file//
    // string displaySize = size//
    
    String size;
    double price = 0.0;
    double salePrice;
    int invAmnt;
    boolean sale = false;
    boolean inStock = true;
    int itemId;
    static String itemName;
    String itemSize;
    static double itemPrice;
    int itemAmnt;
    int itemsInCart;

    // constructor
    public Item(String size, double price, double salePrice, int itemAmnt, boolean sale, boolean inStock) {
        this.size = size;
        this.price = price;
        this.salePrice = salePrice;
        this.itemAmnt = itemAmnt;
        this.sale = sale;
        this.inStock = inStock;
    }

    public Item(){}

    // getters
    public String getSize() {return size;}
    public double getPrice() {return price;}
    public double getSalePrice() {return salePrice;}
    public int getItemAmnt() {return itemAmnt;}
    public boolean getSale() {return sale;}
    public boolean getInStock() {return inStock;}

    // setters
    public void setSize(String size) {this.size = size;}
    public void setPrice(double price) {this.price = price;}
    public void setSalePrice(double salePrice) {this.salePrice = salePrice;}
    public void setItemAmnt(int itemAmnt) {this.itemAmnt = itemAmnt;}
    public void setSale(boolean sale) {this.sale = sale;}
    public void setInStock(boolean inStock) {this.inStock = inStock;}

    // read method
    public void readFile() throws FileNotFoundException{

        Scanner filesc = new Scanner(new File("itemstock.txt"));
        String data; // scans data
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter item id number:");
        int input = sc.nextInt();

        try{
            File file = new File("itemstock.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            while( filesc.hasNextLine() ){
                data = filesc.nextLine();
                String[] a = data.split(" "); // split data up by name price and amount
                itemId = Integer.parseInt(a[0]);
                itemName = a[1];
                itemSize = a[2];
                //color
                itemPrice = Double.parseDouble(a[3]);
                itemAmnt = Integer.parseInt(a[4]);
                if(itemId == input) {
                    String display = sc.next();
                    String b = "itemname";
                    String c = "itemprice";
                    String d = "itemamnt";
                    if(display.equals(b)) {
                        System.out.println(itemName); // displays item name
                    }
                    else if(display.equals(c)) {
                        System.out.println(itemPrice); // displays item price
                    }
                    else if(display.equals(d)) {
                        System.out.println(itemAmnt); //displays item Amount
                    }
                    else{
                        return;
                    }
                    //System.out.println(itemName + "\n" + itemPrice + "\n" + itemAmnt);
                }// end if
            }// end while
            sc.close();
            filesc.close();// end scanner
            br.close();
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
        
    }


    // inventory method
    public int inventory() throws FileNotFoundException{

        Scanner filesc = new Scanner(new File("itemstock.txt"));
        String data; // scans data
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
    
        try {
            File file = new File("itemstock.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
    
            while( filesc.hasNextLine() ){
                data = filesc.nextLine();
                String[] a = data.split(" "); // split data up by name price and amount
                itemId = Integer.parseInt(a[0]);
                itemName = a[1];
                itemSize = a[2];
                itemPrice = Double.parseDouble(a[3]);
                itemAmnt = Integer.parseInt(a[4]);
                if(input == itemId) {
                    itemAmnt = sc.nextInt();
                    System.out.println(itemAmnt + " " + itemName + "'s left in stock.");
                }
            }// end while
            sc.close();
            filesc.close();// end scanner
            br.close(); // close reader

        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
        return itemAmnt;
    } 
    
    // add to cart
    public int addItem() {
        boolean addToCart = false;
        while(addToCart = true) {
            itemAmnt--;
            itemsInCart++;
            System.out.println(itemsInCart);
        }
        return itemAmnt;
    }
        

        /*  invAmnt = 99;
            for(int i = 0; i < a.length; i--){
            while(invAmnt > 0) {
                if(inStock == true) {
                    System.out.println("This item has " + invAmnt + " in stock.");
                } // end if
                else {
                    System.out.println("This item is unavailable.");
                } // end else
            } // end while
        }// end for loop
        return invAmnt; 
    } */

    // on sale method
    public double onSale() throws FileNotFoundException{

        Scanner filesc = new Scanner(new File("itemstock.txt"));
        String data; // scans data
        double saleAmnt;
        double saleMul;
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        try {
            File file = new File("itemstock.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            while( filesc.hasNextLine() ){
                data = filesc.nextLine();
                String[] a = data.split(" "); // split data up by name price and amount
                itemId = Integer.parseInt(a[0]);
                itemName = a[1];
                itemSize = a[2];
                itemPrice = Double.parseDouble(a[3]);
                itemAmnt = Integer.parseInt(a[4]);
                if(input == itemId) {
                    saleAmnt = sc.nextDouble();
                    saleAmnt = .01 * saleAmnt;
                    saleMul = itemPrice * saleAmnt;
                    itemPrice = itemPrice - saleMul;
                    System.out.println("The price for " + itemName + " is " + itemPrice);
                }// end if
            }// end while
            sc.close();
            filesc.close();// end scanner
            br.close(); // close reader
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch


        filesc.close();
        return itemPrice;
    }

    // admin page
    public void admin() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        try{
            File login = new File("admin.txt"); //reads admin file
            BufferedReader br = new BufferedReader(new FileReader(login));
            String user = br.readLine();
            String pass = br.readLine();

            if((username.equals(user)) && (password.equals(pass))) {
                System.out.println("");
            }
            else {
                System.out.println("Incorrect login info provided.");
            }
            br.close();
            sc.close();
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
    }
    

}

