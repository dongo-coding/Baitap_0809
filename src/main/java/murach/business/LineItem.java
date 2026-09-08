package murach.business;

import java.io.Serializable;

public class LineItem implements Serializable {
    private Product product;
    private int quant;
    public LineItem() {}
    public LineItem(Product product, int quant){
        this.product=product;
        this.quant=quant;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quant; }
    public void setQuantity(int quantity) { this.quant = quantity; }

    public double getTotal() {
        return product.getPrice() * quant;
    }
}
