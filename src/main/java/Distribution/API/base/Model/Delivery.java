package Distribution.API.base.Model;

import Distribution.API.base.Controller.Config.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="deliveries")
public class Delivery extends Core{

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String client;

    public Delivery() {
    }

    public Delivery(String client, OrderStatus status) {
        this.status = status;
        this.client = client;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
