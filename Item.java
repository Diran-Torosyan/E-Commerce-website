import java.lang.String;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
                if(input == itemId) {
                    System.out.println(itemName + "\n" + itemPrice + "\n" + itemAmnt);
                }
            }// end while
            sc.close();
            filesc.close();// end scanner
            br.close(); // close reader
        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
        
    }

    // write method
    /*public static void priceUpdate() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("itemstock.txt")); 
            Scanner filesc = new Scanner(new File("itemstock.txt"));
            itemName = filesc.nextDouble();
            if(itemName == ){

            }
            bw.write(String.valueOf(itemPrice));
            bw.close();
            filesc.close();
        } catch (Exception e){
            return;
        }
    } */
    
    
    
    /* price method
    public double realPrice(){
        if(size.equals("XL")||size.equals("XXL")) {
            price = price + 5.00;
        }
        else if(size.equals("S")||size.equals("M")||size.equals("L")){
            price = price + 0;
        }
        else {
            System.out.println("ERROR: Size is invalid.");
        }
        return price;
    } */


    // inventory method
    public int inventory() throws FileNotFoundException{
        /*String itemPos;
        Scanner filesc = new Scanner(new File("itemstock.txt"));
        System.out.println("What item would you like to update?");
        Scanner sc = new Scanner(System.in);
        itemPos =  filesc.nextLine();
        if(itemPos.equals(itemName)) {
            System.out.println("How much would you like to restock?");
            itemAmnt = filesc.nextInt();
            filesc.close();
            System.out.println(itemAmnt); */

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
        /* Scanner filesc = new Scanner(new File("itemstock.txt"));
        double saleAmnt;
        if(sale == true){
            System.out.println("SALE");
            saleAmnt = .1 * filesc.nextInt();
            price = itemPrice * saleAmnt;
            itemPrice = itemPrice - price;
            System.out.println(itemPrice);
        } */

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
                }
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
    

}

