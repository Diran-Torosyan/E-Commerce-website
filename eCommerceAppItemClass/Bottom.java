public class Bottom extends Item{
        
    // instance variables
    String skirt;
    String shorts;
    String jeans;
    String pants;

    // constructor
    public Bottom(String skirt, String shorts, String jeans, String pants, String size, String price, double salePrice, int invAmnt, boolean sale, boolean inStock) {
        super(size, price, salePrice, invAmnt, sale, inStock);
        this.skirt = skirt;
        this.shorts = shorts;
        this.jeans = jeans;
        this.pants = pants;
    }
}
