package Distribution.API.base.Service;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import Distribution.API.base.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Integer> getOrdersByUser(int userId) {
        return storageSrv.getOrders(userId);
    }
}
