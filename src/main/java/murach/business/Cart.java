package murach.business;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Cart implements Serializable  {

    public List<LineItem> items;
    public Cart() {
        items = new ArrayList<>();

    }

    public List<LineItem> getItems() {return items;}

    public void addItem(LineItem item){
        String id = item.getProduct().getCode();
        int quant = item.getQuantity();

        for (LineItem lineItem : items) {
            if (lineItem.getProduct().getCode().equals(id)) {
                lineItem.setQuantity(lineItem.getQuantity() + quant);
                return;
            }
        }
        items.add(item);

    }

    public void removeItem(LineItem item) {
        String id = item.getProduct().getCode();
        items.removeIf(lineItem -> lineItem.getProduct().getCode().equals(id));
    }

    public void updateItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();

        for (LineItem lineItem : items) {
            if (lineItem.getProduct().getCode().equalsIgnoreCase(code)) {
                if (quantity > 0) {
                    lineItem.setQuantity(quantity);
                } else {
                    removeItem(item);
                }
                return;
            }
        }
    }

}
