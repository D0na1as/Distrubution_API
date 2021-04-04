package Distribution.API.base.Service;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import Distribution.API.base.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private StorageService storageSrv;

    public int getClientByOrder(long orderId) {
        return orderRepo.getClientByOrder(orderId);
    }

    public boolean changeStatus(long orderId, OrderStatus status) {
        orderRepo.setStatus(orderId, status);
        return true;
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Item> getOrderItems(long orderId) {
        return storageSrv.getOrderItems(orderId);
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Long> orders = storageSrv.getOrders(userId);
        List<Order> list = new ArrayList<>();
        for (long order:orders) {
            list.add(orderRepo.getOrder(order));
        }
        return list;

    }

    public List<Integer> getOrdersByClient(int clientId) {
        return orderRepo.getOrdersByClient(clientId);
    }

    public List<Order> getByUserAndStatus(int userId, OrderStatus status) {
        List<Order> allOrders = getOrdersByUser(userId);
        List<Order> newList = new ArrayList<>();
        for (Order order:allOrders) {
            if (order.getStatus()==status) {
                newList.add(order);
            }
        }
        return newList;
    }
}
