package Distribution.API.base.Service;

import Distribution.API.base.Model.Item;
import Distribution.API.base.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public void addItem(Item itemNr) {
    }

    public List<Long> getCart(String client) {
        return cartRepo.getCart(client);
    }

    public Boolean removeItem(long id) {
        return cartRepo.removeCart(id);
    }

    public Boolean clean() {
        return cartRepo.clean();
    }
}
