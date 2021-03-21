package Distribution.API.base.Service;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public int getClient(long orderId) {
        return orderRepo.getClient(orderId);
    }

    public boolean changeStatus(OrderStatus status) {
    }

    public long createOrder() {
        return orderRepo.createOrder();
    }
}
