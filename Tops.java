public class Tops extends Item{
    
    // instance variables
    String tshirt;
    String buttonUp;
    String hoodie;
    String jacket;
    String blouse;


    // constructor
    public Tops(String tshirt, String buttonUp, String hoodie, String jacket, String blouse, String size, String price, double salePrice, int invAmnt, boolean sale, boolean inStock) {
        super(size, price, salePrice, invAmnt, sale, inStock);
        this.tshirt = tshirt;
        this.buttonUp = buttonUp;
        this.hoodie = hoodie;
        this.jacket = jacket;
        this.blouse = blouse;
    }

    public void realPrice() {
        super.realPrice();
        System.out.println(tshirt + " =" + realPrice());
    
}
