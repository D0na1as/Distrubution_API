package Distribution.API.base.Model;

import javax.persistence.*;

@Entity
@Table(name="storage")
public class Item extends Core{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;
    private String serial;
    private int quantity;

    public Item() {
    }

    public Item(String title, String serial, int quantity) {
        this.title = title;
        this.serial = serial;
        this.quantity = quantity;
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
    }}
