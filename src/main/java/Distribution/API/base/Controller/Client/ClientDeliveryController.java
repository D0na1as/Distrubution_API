package Distribution.API.base.Controller.Client;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Controller.Exceptions.CheckObject;
import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Delivery;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.CartService;
import Distribution.API.base.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1/client/order")
public class ClientDeliveryController {

    @Autowired
    private DeliveryService deliverySrv;
    @Autowired
    private CheckObject check;
//    @Autowired
//    private StorageService storageSrv;
//    @Autowired
//    private AccountService accountSrv;
    @Autowired
    private CartService cartSrv;

    @PostMapping( value = "/" )
    public ResponseEntity createOrder()  {
        List<Cart> cart = cartSrv.getCart();
        check.checkCartEmpty(cart);
        return ResponseEntity.ok(deliverySrv.createOrder());
    }

    @GetMapping( value = "/status/{status}" )
    public ResponseEntity getOrdersByStatus(@PathVariable("status") OrderStatus status)  {
        List<Delivery> deliveries = deliverySrv.getByClientAndStatus( "client", status);
        //check.checkDelivEmpty(deliveries);
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping( value = "/{orderId}" )
    public ResponseEntity updateOrder(@PathVariable("orderId") long orderId,
                                      @RequestParam("status") OrderStatus status){
        String client = deliverySrv.getClientByOrder(orderId);
        check.checkStringEmpty( client);
        deliverySrv.changeStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping( value = "/{orderId}" )
    public ResponseEntity getOrder(@PathVariable("orderId") long orderId)  {
        List<Item> order = deliverySrv.getOrderItems(orderId);
        check.checkItemEmpty(order);
        return ResponseEntity.ok(order);
    }

//    @RequestMapping( value = "/all/{clientId}", method = RequestMethod.GET)
//    public ResponseEntity getOrders(@PathVariable("clientId") int clientId)  {
//        List<Integer> orders = orderSrv.getOrdersByClient(clientId);
//        if (orders.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(orders);
//    }

//    @RequestMapping( value = "/{orderId}/{status}", method = RequestMethod.PUT)
//    public ResponseEntity updateOrderStatus(@PathVariable("orderId") int orderId,
//                                            @PathVariable("status") OrderStatus status){
//        int client = orderSrv.getClientByOrder(orderId);
//        if (client==0) {
//            return ResponseEntity.notFound().build();
//        }
//        orderSrv.changeStatus(orderId, status);
//        return ResponseEntity.ok().build();
//    }
}
