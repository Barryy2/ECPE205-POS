public class Prods {

    String Sku;
    String Name;
    String Price;

    public Prods(String sku, String name, String price) {
        Sku = sku;
        Name = name;
        Price = price;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
