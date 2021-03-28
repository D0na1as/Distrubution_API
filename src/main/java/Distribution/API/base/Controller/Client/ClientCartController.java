package Distribution.API.base.Controller.Client;

import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.AccountService;
import Distribution.API.base.Service.CartService;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/v1/client/cart")
public class ClientCartController {

    @Autowired
    private CartService cartSrv;
    @Autowired
    private StorageService storageSrv;
    String client = "Client";
//
//    @RequestMapping( value = "/", method = RequestMethod.GET )
//    public ResponseEntity addItem()  {
//        List<Long> cart = cartSrv.getCart(client);
//        if (cart.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cart);
//    }

//    @RequestMapping( value = "/item", method = RequestMethod.PUT )
//    public ResponseEntity addItem(@RequestParam("item") long item)  {
//        Item itemNr = storageSrv.getItem(item);
//        if (itemNr==null) {
//            return ResponseEntity.notFound().build();
//        }
//        cartSrv.addItem(itemNr);
//        return ResponseEntity.ok().build();
//    }
//
//    @RequestMapping( value = "/item", method = RequestMethod.DELETE)
//    public ResponseEntity removeItem(@RequestParam("item") long item)  {
//        Boolean result = cartSrv.removeItem(item);
//        if (result) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @RequestMapping( value = "/clean", method = RequestMethod.DELETE)
//    public ResponseEntity removeItem()  {
//        Boolean result = cartSrv.clean();
//        if (result) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

}
