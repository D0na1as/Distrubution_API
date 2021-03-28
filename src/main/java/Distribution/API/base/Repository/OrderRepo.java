package Distribution.API.base.Repository;

import Distribution.API.base.Controller.Config.OrderStatus;
import Distribution.API.base.Model.Account;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Model.Order;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {


    String table = "orders";

    //Queries
//    String createOrder = "INSERT INTO "+ table +"(client ";
//    String pageItems = "SELECT * FROM "+ table +" LIMIT ?1,?2";
    String getClient = "SELECT IFNULL((SELECT CLIENT FROM "+ table +" WHERE id=?), 0);";
    String updateStatus = "UPDATE " + table + " SET status=:#{#status.name()} WHERE id=?1";
    String getClientByClient = "SELECT id FROM "+ table +" WHERE client=?";

    //Query execution
//    @Query(nativeQuery = true, value = entitiesCount)
//    long storageItemCount();

//    @Query(nativeQuery = true, value = pageItems)
//    List<Item> getPageItems(long first, long count);
    
    @Query(nativeQuery = true, value = getClient)
    int getClientByOrder(long orderId);

    @Query(nativeQuery = true, value = getClientByClient)
    List<Integer> getOrdersByClient(int clientId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = updateStatus)
    void setStatus(long orderId, @Param("status") OrderStatus status);

}
