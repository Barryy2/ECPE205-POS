import javax.swing.*;

public class Prods {

    String Sku;
    String Name;
    String Price;
    String Commodities;

    public Prods(String sku, String name, String price, String commodities) {
        Sku = sku;
        Name = name;
        Price = price;
        Commodities = commodities;
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

    public String getCommodities() {
        return Commodities;
    }

    public void setCommodities(String commodities) {
        Commodities = commodities;
    }
}
