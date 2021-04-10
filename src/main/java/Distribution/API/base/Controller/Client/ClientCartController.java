package Distribution.API.base.Controller.Client;

import Distribution.API.base.Controller.Exceptions.CheckObject;
import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.CartService;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    String client = "Client";
//
    @PostMapping( value = "/item")
    public ResponseEntity addToCart(@RequestParam("itemId") long id,
                                    @RequestParam("quantity") int qnt)  {

        Item item = storageSrv.getItem(id);
        check.checkIfNull(item);
        long cartId = cartSrv.getItemId(id);
        check.checkById(cartId);
        Cart cart = cartSrv.addItem(new Cart(id, qnt));
        return ResponseEntity.ok(cart);
    }

    @GetMapping( value = "/" )
    public ResponseEntity getCart()  {
        return ResponseEntity.ok(cartSrv.getCart());
    }

    @DeleteMapping( value = "/item/{id}" )
    public ResponseEntity removeItem(@PathVariable("id") long id)  {
        check.checkById(cartSrv.removeItem(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping( value = "/clean" )
    public ResponseEntity removeItem()  {
        cartSrv.clean();
        return ResponseEntity.ok().build();
    }
//
//    @PutMapping( value = "/item")
//    public ResponseEntity updateCart(@RequestParam("itemId") long id,
//                                     @RequestParam("qnt") int qnt)  {
//        Item item = storageSrv.getItem(id);
////        if (item==null) {
////            return ResponseEntity.notFound().build();
////        }
//        item.setQuantity(qnt);
//        storageSrv.addItem(item);
//        return ResponseEntity.ok().build();
//    }


}
