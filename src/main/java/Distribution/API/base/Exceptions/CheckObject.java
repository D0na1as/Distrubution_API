package Distribution.API.base.Exceptions;

import Distribution.API.base.Model.Account;
import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Delivery;
import Distribution.API.base.Model.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class CheckObject {

    public void checkById (Long numb) {
        if (numb==0) {
            throw new ResponseStatusException(NOT_FOUND, "Item not found!");
        }
    }

    public void checkCart (Long numb) {
        if (numb>0) {
            throw new ResponseStatusException(BAD_REQUEST, "Item already in cart!");
        }
    }

    public void checkCount (int count1, int count2) {
        if (count1<count2) {
            throw new ResponseStatusException(NOT_FOUND, "Not enough items in storage!");
        }
    }

    public void checkPutCount (int count) {
        if (count<1) {
            throw new ResponseStatusException(BAD_REQUEST, "Must be at least one item!");
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

    public void checkIfNull (Account account) {
        if (account==null) {
            throw new ResponseStatusException(NOT_FOUND, "Account not found!");
        }
    }

    public void checkIfNull (Cart cart) {
        if (cart==null) {
            throw new ResponseStatusException(BAD_REQUEST, "Not enough items!");
        }
    }

    //Checking if client exists
    public void checkStringEmpty (String val) {
        if (val.equals("0")) {
            throw new ResponseStatusException(NOT_FOUND, "Client not found!");
        }
    }
}
