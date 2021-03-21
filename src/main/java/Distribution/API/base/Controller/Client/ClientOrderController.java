package Distribution.API.base.Controller.Client;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Item;
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

    @RequestMapping( value = "/", method = RequestMethod.POST)
    public ResponseEntity addOrder(@PathVariable("orderId") long orderId)  {
        List<Item> order = storageSrv.getOrder(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.ok(orderSrv.createOrder());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping( value = "/all/{userId}", method = RequestMethod.GET)
    public ResponseEntity getOrders(@PathVariable("userId") int userId)  {
        List<Integer> orders = storageSrv.getOrders(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @RequestMapping( value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("orderId") long orderId)  {
        List<Item> order = storageSrv.getOrder(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping( value = "/{orderId}/{status}", method = RequestMethod.PUT)
    public ResponseEntity updateOrderStatus(@PathVariable("orderId") int orderId,
                                      @PathVariable("status") OrderStatus status){
        int client = orderSrv.getClient(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        orderSrv.changeStatus(status);
        return ResponseEntity.ok().build();
    }
}
