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
@RequestMapping("/v1/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderSrv;
    @Autowired
    private StorageService storageSrv;
    @Autowired
    private AccountService accountSrv;

    @RequestMapping( value = "/all/{userId}", method = RequestMethod.GET)
    public ResponseEntity getOrders(@PathVariable("userId") int userId)  {
        List<Integer> orders = orderSrv.getOrdersByUser(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @RequestMapping( value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrderById(@PathVariable("orderId") long orderId)  {
        List<Item> order = orderSrv.getOrderItems(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping( value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@PathVariable("orderId") long orderId,
                                      @RequestParam("status") OrderStatus status){
        int client = orderSrv.getClientByOrder(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        orderSrv.changeStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    @RequestMapping( value = "/{orderId}/client", method = RequestMethod.GET)
    public ResponseEntity getClientOrder(@PathVariable("orderId") long orderId)  {
        int client = orderSrv.getClientByOrder(orderId);
        if (client==0) {
            return ResponseEntity.notFound().build();
        }
        Account account = accountSrv.getAccountById(client);
        return ResponseEntity.ok(account);
    }
}
