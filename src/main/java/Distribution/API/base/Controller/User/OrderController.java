package Distribution.API.base.Controller.User;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Account;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/v1/user/storage")
public class OrderController {

    @Autowired
    private OrderService orderSrv;
    @Autowired
    private StorageService storageSrv;
    @Autowired
    private AccountService accountSrv;

    @RequestMapping( value = "/orders/{userId}", method = RequestMethod.GET)
    public ResponseEntity getOrders(@PathVariable("userId") int userId)  {
        List<Integer> orders = storageSrv.getOrders(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @RequestMapping( value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("orderId") int orderId)  {
        List<Item> order = storageSrv.getOrder(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping( value = "/order/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@PathVariable("orderId") int orderId,
                                  @RequestParam("status") OrderStatus status){
        int client = orderSrv.getClient(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        orderSrv.changeStatus(status);
        return ResponseEntity.ok().build();
    }

    @RequestMapping( value = "/order/{orderId}/client", method = RequestMethod.GET)
    public ResponseEntity getOrderClient(@PathVariable("orderId") int orderId)  {
        int client = orderSrv.getClient(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        Account account = accountSrv.getAccountById(client);
        return ResponseEntity.ok(account);
    }
}
