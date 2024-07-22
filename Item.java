import java.lang.String;

public class Item {

    // instance variables
    public String size;
    public String price;
    public double salePrice;
    public int invAmnt;
    public boolean sale = false;
    public boolean inStock = true;

    // constructor
    public Item(String size, String price, double salePrice, int invAmnt, boolean sale, boolean inStock) {
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
    public String getPrice() {return price;}
    public double getSalePrice() {return salePrice;}
    public int getInvAmnt() {return invAmnt;}
    public boolean getSale() {return sale;}
    public boolean getInStock() {return inStock;}

    // setters
    public void setSize(String size) {
        this.size = size;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public void setInvAmnt(int invAmnt) {
        this.invAmnt = invAmnt;
    }
    public void setSale(boolean sale) {
        this.sale = sale;
    }
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    // price method
    public void realPrice(){
        if(size.equals("XL")||size.equals("XXL")) {
            price = "34.99";
        }
        else {
            price = "29.99";
        }
    }

    public void sizeGuide() {
        String small = "S";
        String medium = "M";
        String large = "L";
        String xl = "XL";
        String xxl = "XXL";
    }

    // inventory method
    public static void inventory(int invAmnt, boolean inStock) {
        invAmnt = 99;

        for(int i = invAmnt; i > 0; i--){
            while(invAmnt > 0) {
                if(inStock == true) {
                    System.out.println("This item is still in stock.");
                } // end if
                else {
                    System.out.println("This item is unavailable.");
                } // end else
            } // end while
        }// end for loop
    }

    // on sale method
    public static void onSale(boolean sale) {
        if(sale == true){
            System.out.println("This item is on sale.");
        }
        else
            return;
    }
    

}

