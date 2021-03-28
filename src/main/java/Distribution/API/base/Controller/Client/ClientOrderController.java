package Distribution.API.base.Controller.Client;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import Distribution.API.base.Service.AccountService;
import Distribution.API.base.Service.OrderService;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1/client/order")
public class ClientOrderController {

    @Autowired
    private OrderService orderSrv;
    @Autowired
    private StorageService storageSrv;
    @Autowired
    private AccountService accountSrv;
//TODO method
//    @RequestMapping( value = "/", method = RequestMethod.POST)
//    public ResponseEntity addOrder(@PathVariable("orderId") long orderId)  {
//        List<Item> order = storageSrv.getOrderItems(orderId);
//        if (order.isEmpty()) {
//            //TODO add real order
//            return ResponseEntity.ok(orderSrv.createOrder(new Order()));
//        }
//        return ResponseEntity.badRequest().build();
//    }

    @RequestMapping( value = "/all/{clientId}", method = RequestMethod.GET)
    public ResponseEntity getOrders(@PathVariable("clientId") int clientId)  {
        List<Integer> orders = orderSrv.getOrdersByClient(clientId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @RequestMapping( value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("orderId") long orderId)  {
        List<Item> order = storageSrv.getOrderItems(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping( value = "/{orderId}/{status}", method = RequestMethod.PUT)
    public ResponseEntity updateOrderStatus(@PathVariable("orderId") int orderId,
                                            @PathVariable("status") OrderStatus status){
        int client = orderSrv.getClientByOrder(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        orderSrv.changeStatus(orderId, status);
        return ResponseEntity.ok().build();
    }
}
