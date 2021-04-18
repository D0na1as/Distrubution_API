package Distribution.API.base.Controller.Client;


import Distribution.API.base.Exceptions.CheckObject;
import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.CartService;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/client/cart")
public class ClientCartController {

    @Autowired
    private CartService cartSrv;
    @Autowired
    private StorageService storageSrv;
    @Autowired
    private CheckObject check;

    @PostMapping( value = "/item")
    public ResponseEntity addToCart(@RequestParam("itemId") long id,
                                    @RequestParam("quantity") int qnt)  {

        Item item = storageSrv.getItem(id);
        check.checkIfNull(item);
        check.checkCount(item.getQuantity(), qnt);
        long cartId = cartSrv.getItemId(id);
        if (cartId!=0) {
            check.checkCart(cartId);
        }
        Cart cart = cartSrv.addItem(new Cart(id, qnt));
        return ResponseEntity.ok(cart);
    }

    @GetMapping( value = "/" )
    public ResponseEntity getCart()  {
        return ResponseEntity.ok(cartSrv.getCart());
    }

    @DeleteMapping( value = "/item/{id}" )
    public ResponseEntity removeItem(@PathVariable("id") long id)  {
        long cartId = cartSrv.getItemId(id);
        if (cartId==0) {
            check.checkById(cartId);
        }
        check.checkById(cartSrv.removeItem(cartId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping( value = "/clean" )
    public ResponseEntity removeItem()  {
        cartSrv.clean();
        return ResponseEntity.ok().build();
    }

    @PutMapping( value = "/item")
    public ResponseEntity updateCart(@RequestParam("itemId") long id,
                                     @RequestParam("quantity") int qnt)  {
        long cartId = cartSrv.getItemId(id);
        if (cartId == 0) {
            check.checkById(cartId);
        }
        if (qnt == 0) {
            check.checkById(cartSrv.removeItem(cartId));
            return ResponseEntity.ok().build();
        } else {
            Cart cart = cartSrv.getItemById(cartId);
            Item item = storageSrv.getItem(id);
            if (qnt < 0 || (cart.getCount() + item.getQuantity()) < qnt) {
                check.checkIfNull(cart = null);
            }
            check.checkById(cartSrv.removeItem(cartId));
            cart = cartSrv.addItem(new Cart(id, qnt));
            check.checkIfNull(cart);

            return ResponseEntity.ok().build();
        }
    }


}
