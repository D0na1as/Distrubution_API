package Distribution.API.base.Controller.Exceptions;

import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Delivery;
import Distribution.API.base.Model.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class CheckObject {

    public void checkById (Long numb) {
        if (numb==0) {
            throw new ResponseStatusException(NOT_FOUND, "Item not found!");
        }
    }

    public void checkItemEmpty (List<Item> items) {
        if (items.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Item not found!");
        }
    }

    public void checkDelivEmpty (List<Delivery> deliveries) {
        if (deliveries.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Delivery not found!");
        }
    }

    public void checkCartEmpty (List<Cart> deliveries) {
        if (deliveries.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Cart is empty!");
        }
    }

    public void checkIfNull (Item item) {
        if (item==null) {
            throw new ResponseStatusException(NOT_FOUND, "Item not found!");
        }
    }
}
