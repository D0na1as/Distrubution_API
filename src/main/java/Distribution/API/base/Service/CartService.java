package Distribution.API.base.Service;

import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private StorageService storageSrv;

    public Cart addItem(Cart cart) {
        if (getFromStorage(cart)) {
            return cartRepo.save(cart);
        } else {
            return null;
        }
    }

    public List<Cart> getCart() {
        return (List<Cart>) cartRepo.findAll();
    }

    public long removeItem(long id) {
        Cart cart = getItemById(id);
        if (addToStorage(cart)) {
            cartRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    private Cart getItemById(long id) {
        return cartRepo.findById(id).orElse(null);
    }


    public long getItemId(long id) {
        return cartRepo.getItemId(id);
    }


    public void clean() {
        List<Cart> list =  getCart();
        for (Cart item:list) {
            addToStorage(item);
        }
        cartRepo.deleteAll();
    }

    public void forceClean() {
        cartRepo.deleteAll();
    }

    private Boolean getFromStorage(Cart cart){
        Item temp = storageSrv.getItem(cart.getItemId());
        int dif = temp.getQuantity()-cart.getCount();
        if (dif<1 || temp.getQuantity()<1) {
            return false;
        }
        temp.setQuantity(temp.getQuantity()-cart.getCount());
        storageSrv.updateItem(temp);
        return true;
    }

    private Boolean addToStorage(Cart cart){
        Item temp = storageSrv.getItem(cart.getItemId());
        temp.setQuantity(temp.getQuantity()+cart.getCount());
        storageSrv.updateItem(temp);
        return true;
    }

}
