package Distribution.API.base.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {

    @Id
    private long id;
    private String title;
    private String serial;
    private int quantity;
    private int storage;
    @Transient
    private long order;

    public Item() {
    }

    public Item(String title, String serial, int quantity, int storage) {
        this.title = title;
        this.serial = serial;
        this.quantity = quantity;
        this.storage = storage;
    }

    public Item(String title, String serial, int quantity, int storage, long order) {
        this.title = title;
        this.serial = serial;
        this.quantity = quantity;
        this.storage = storage;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
