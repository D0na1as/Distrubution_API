package Distribution.API.base.Repository;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

    String table = "orders";

    //Queries
//    String createOrder = "INSERT INTO "+ table +"(client ";
//    String pageItems = "SELECT * FROM "+ table +" LIMIT ?1,?2";
    String updateStatus = "UPDATE `" + table + "` SET status=:#{#status.name()} WHERE id=?1";
    String getOrdersByClient = "SELECT * FROM `"+ table +"` WHERE client=?1";
    String getOrder = "SELECT * FROM `"+ table +"` WHERE id=?1";

    //Query execution
//    @Query(nativeQuery = true, value = entitiesCount)
//    long storageItemCount();

//    @Query(nativeQuery = true, value = pageItems)
//    List<Item> getPageItems(long first, long count)

    @Query(nativeQuery = true, value = getOrdersByClient)
    List<Order> getOrdersByClient(int clientId);

    @Query(nativeQuery = true, value = getOrder)
    Order getOrder(long order);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = updateStatus)
    void setStatus(long orderId, @Param("status") OrderStatus status);

}
