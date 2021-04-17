package Distribution.API.base.Controller.User;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Controller.Exceptions.CheckObject;
import Distribution.API.base.Controller.Exceptions.ExceptionHandling;
import Distribution.API.base.Model.Account;
import Distribution.API.base.Model.Delivery;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import Distribution.API.base.Service.AccountService;
import Distribution.API.base.Service.DeliveryService;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1/user/order")
public class UserDeliveryController {

    @Autowired
    private DeliveryService deliverySrv;
    @Autowired
    private CheckObject check;
//    @Autowired
//    private StorageService storageSrv;
    @Autowired
    private AccountService accountSrv;


    @GetMapping( value = "/status/{status}" )
    public ResponseEntity getOrdersByStatus(@PathVariable("status") OrderStatus status)  {
        List<Delivery> deliveries = deliverySrv.getByStatus(status);
        //check.checkDelivEmpty(deliveries);
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping( value = "/{orderId}" )
    public ResponseEntity updateOrder(@PathVariable("orderId") long orderId,
                                      @RequestParam("status") OrderStatus status){
        String client = deliverySrv.getClientByOrder(orderId);
        check.checkStringEmpty(client);
//        if (client==0) {
//            return ResponseEntity.notFound().build();
//        }
        deliverySrv.changeStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping( value = "/{orderId}" )
    public ResponseEntity getOrder(@PathVariable("orderId") long orderId)  {
        List<Item> order = deliverySrv.getOrderItems(orderId);
        check.checkItemEmpty(order);
//        if (order.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(order);
    }

    @RequestMapping( value = "/{orderId}/client", method = RequestMethod.GET)
    public ResponseEntity getClientOrder(@PathVariable("client") long orderId)  {
        String client = deliverySrv.getClientByOrder(orderId);
        check.checkStringEmpty(client);
        Account account = accountSrv.getAccountByEmail(client);
        return ResponseEntity.ok(account);
    }

//    @GetMapping( value = "/all" )
//    public ResponseEntity getOrders()  {
//        List<Order> orders = orderSrv.getAllOrders();
////        if (orders.isEmpty()) {
////            return ResponseEntity.notFound().build();
////        }
//        return ResponseEntity.ok(orders);
//    }
//
//    @GetMapping( value = "/{status}" )
//    public ResponseEntity getOrdersByStatus(@PathVariable("status") OrderStatus status)  {
//        List<Delivery> orders = deliverySrv.getByStatus(status);
////        if (orders.isEmpty()) {
////            return ResponseEntity.notFound().build();
////        }
//        return ResponseEntity.ok(orders);
//    }
//
//    @PutMapping( value = "/{orderId}" )
//    public ResponseEntity updateOrder(@PathVariable("orderId") long orderId,
//                                      @RequestParam("status") OrderStatus status){
////        int client = orderSrv.getClientByOrder(orderId);
////        if (client==0) {
////            return ResponseEntity.notFound().build();
////        }
//        orderSrv.changeStatus(orderId, status);
//        return ResponseEntity.ok().build();
//    }

}
