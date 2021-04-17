package Distribution.API.base.Service;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Cart;
import Distribution.API.base.Model.Delivery;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import Distribution.API.base.Repository.CartRepo;
import Distribution.API.base.Repository.DeliveryRepo;
import Distribution.API.base.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private CartService cartSrv;
    @Autowired
    private StorageService storageSrv;

    public String getClientByOrder(long orderId) {
        return deliveryRepo.getClientByOrder(orderId);
    }

    public long createOrder(String client) {
        Delivery delivery = deliveryRepo.save(new Delivery(client, OrderStatus.pending));
        List<Cart> cart = cartSrv.getCart();
        for (Cart item:cart) {
            orderRepo.save(new Order(delivery.getId(), item.getItemId(), item.getCount()));
        }
        cartSrv.forceClean();
        return delivery.getId();
    }


    public List<Delivery> getByStatus(OrderStatus status) {
        return deliveryRepo.getByStatus(status);
    }

    public void changeStatus(long orderId, OrderStatus status) {
        deliveryRepo.setStatus(orderId, status);
    }

    public List<Item> getOrderItems(long orderId) {
        return storageSrv.getOrderItems(orderId);
    }
//
//    public List<Order> getOrders(int clientId) {
//        List<Delivery> orders = deliveryRepo.getOrders(userId);
//        List<Order> list = new ArrayList<>();
//        for (long order:orders) {
//            list.add(orderRepo.getOrder(order));
//        }
//        return list;
//
//    }

    public List<Delivery> getByClientAndStatus(String client, OrderStatus status) {
        return deliveryRepo.getByClientAndStatus(client, status);
    }

//    public List<Order> getByUserAndStatus(int userId, OrderStatus status) {
//        List<Order> allOrders = getOrdersByUser(userId);
//        List<Order> newList = new ArrayList<>();
//        for (Order order:allOrders) {
//            if (order.getStatus()==status) {
//                newList.add(order);
//            }
//        }
//        return newList;
//    }
//
//    public List<Order> getAllOrders() {
//        List<Order> orders = new ArrayList<>();
//        orderRepo.findAll().forEach(orders::add);
//        return orders;
//    }

}
