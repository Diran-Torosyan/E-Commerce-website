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
    String itemName;
    String itemSize;
    static double itemPrice;
    int itemAmnt;

    // constructor
    public Item(String size, double price, double salePrice, int invAmnt, boolean sale, boolean inStock) {
        this.size = size;
        this.price = price;
        this.salePrice = salePrice;
        this.invAmnt = invAmnt;
        this.sale = sale;
        this.inStock = inStock;
    }

    public Item(){}

    // getters
    public String getSize() {return size;}
    public double getPrice() {return price;}
    public double getSalePrice() {return salePrice;}
    public int getInvAmnt() {return invAmnt;}
    public boolean getSale() {return sale;}
    public boolean getInStock() {return inStock;}

    // setters
    public void setSize(String size) {this.size = size;}
    public void setPrice(double price) {this.price = price;}
    public void setSalePrice(double salePrice) {this.salePrice = salePrice;}
    public void setInvAmnt(int invAmnt) {this.invAmnt = invAmnt;}
    public void setSale(boolean sale) {this.sale = sale;}
    public void setInStock(boolean inStock) {this.inStock = inStock;}

    // read method
    public void readFile() throws FileNotFoundException{
        Scanner filesc = new Scanner(new File("itemstock.txt"));
        String data; // scans data

        try {
            File file = new File("itemstock.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            } // end while

            while( filesc.hasNextLine() ){
                data = filesc.nextLine();
                String[] a = data.split(" "); // split data up by name price and amount
                itemName = a[0];
                itemSize = a[1];
                itemPrice = Double.parseDouble(a[2]);
                itemAmnt = Integer.parseInt(a[3]);
            }// end while
            filesc.close();// end scanner
            br.close(); // close reader

        } catch(IOException e) {
            System.out.println("File not found.");
        } // end try/catch
    }

    // write method
    public static void priceUpdate() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("itemstock.txt")); 
            Scanner filesc = new Scanner(new File("itemstock.txt"));
            itemPrice = filesc.nextDouble();
            bw
        } catch {

        }
    }
    
    
    
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
    public static int inventory(int invAmnt, boolean inStock) {
        invAmnt = 99;

        for(int i = invAmnt; i > 0; i--){
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
    }

    // on sale method
    public double onSale(boolean sale) throws FileNotFoundException{
        Scanner filesc = new Scanner(new File("itemstock.txt"));
        int saleAmnt;
        if(sale == true){
            System.out.println("SALE");
            saleAmnt = filesc.nextInt();
            itemPrice = price * saleAmnt;
            System.out.println(itemPrice);
        }
        filesc.close();
        return itemPrice;
    }
    

}

