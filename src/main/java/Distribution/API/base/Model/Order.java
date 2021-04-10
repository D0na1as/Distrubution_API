package Distribution.API.base.Model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order extends Core{

    private long orderId;
    private long itemId;
    private int quantity;

    public Order() {
    }

    public Order(long orderId, long itemId, int quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
