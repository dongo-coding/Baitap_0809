package murach.business;

import java.io.Serializable;

public class Product implements Serializable{
    private String id;
    private String desc;
    private double price;

    public Product() {};

    public Product(String id, String desc, double price){
        this.id=id;
        this.desc=desc;
        this.price=price;

    }
    public String getCode() { return id; }
    public void setCode(String id) { this.id= id; }

    public String getDescription() { return desc; }
    public void setDescription(String description) { this.desc = desc; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
