import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Item {

    // instance variables

    // string size = read from file//
    // string displaySize = size//
    
    String itemName, size, itemSize;
    double price = 0.0, itemPrice, salePrice;
    int itemId, itemAmnt, itemsInCart;
    boolean sale = false, inStock = true;


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

        Scanner filesc = new Scanner(new File("itemstock.txt")); //scans text file
        Scanner sc = new Scanner(System.in); //scans user input
        String data;
        double saleAmnt, saleMul;

        System.out.println("Enter item id number:");
        int input = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter case output: \n\nshow: displays item info\nrestock: updates inventory amount\nitemsale: updates sale price");
        String type = sc.nextLine();

        try{
            File file = new File("itemstock.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            while( filesc.hasNextLine() ){
                data = filesc.nextLine();
                String[] a = data.split(" "); // split data up by section
                itemId = Integer.parseInt(a[0]);
                itemName = a[1];
                //itemSize = a[1];
                //itemColors
                itemPrice = Double.parseDouble(a[2]);
                itemAmnt = Integer.parseInt(a[3]);

                
                if(itemId == input) { // checks if the item id in the text matches the user input
                    switch(type){
                        case "show": // displays item info
                            System.out.println("What would you like to display?"); 
                            String display = sc.next(); // choose variable to display
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
                            else {return;}
                            break; // end case

                        case "restock": // restocks inventory by amount in user input
                            System.out.println("What would you like the new inventory amount to be?");
                            itemAmnt = sc.nextInt();
                            System.out.println("There are " + itemAmnt + " " + itemName + "'s left in stock.");
                            break; // end case

                        case "itemsale": // updates price of item based on sale amount applied
                            System.out.println("What discount would you like to add?");
                            saleAmnt = sc.nextDouble();
                            saleAmnt = .01 * saleAmnt;
                            saleMul = itemPrice * saleAmnt;
                            itemPrice = itemPrice - saleMul;
                            System.out.println("The price for " + itemName + " is " + itemPrice); //round sale price so only 2 decimals
                            break; // end case

                    }// end switch
                }// end if
            }// end while
            br.close(); //closes buffered reader
            sc.close(); //closes input scanner
            filesc.close();// closes text scanner

        } 
        catch(IOException e) {System.out.println("File not found.");} // end try/catch   
    } // end readFile

    // add to cart
    public int addItem() {
        itemsInCart = 0;
        boolean addToCart = false;
        if(addToCart == true) {
            itemAmnt--;
            itemsInCart++;
            System.out.println(itemsInCart);
        }
        return itemAmnt;
    }
        

        /* for(int i = itemAmnt; i > 0; i--){
            while(invAmnt > 0) {
                if(inStock == true) {
                    System.out.println("This item has " + invAmnt + " in stock.");
                } // end if
                else {
                    instock = false;
                    System.out.println("This item is unavailable.");
                } // end else
            } // end while
        }// end for loop
        return invAmnt; 
    } */
    
} // end item class

