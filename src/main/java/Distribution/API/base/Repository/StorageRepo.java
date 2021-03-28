package Distribution.API.base.Repository;

import Distribution.API.base.Model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepo extends CrudRepository<Item, Long> {

    String storage = "storage";
    String deliveries = "deliveries";

    //Queries
    String entitiesCount = "SELECT COUNT(*) FROM "+ storage;
    String pageItems = "SELECT * FROM "+ storage +" LIMIT ?1,?2";
    String getOrderItems = "SELECT * FROM "+ deliveries +" WHERE `order`=?";
    String getOrderList = "SELECT `order` FROM "+ deliveries +" WHERE `storage`=?";

    //Query execution
    @Query(nativeQuery = true, value = entitiesCount)
    long storageItemCount();

    @Query(nativeQuery = true, value = pageItems)
    List<Item> getPageItems(long first, long count);

    @Query(nativeQuery = true, value = getOrderItems)
    List<Item> getOrderItems(long orderId);

    @Query(nativeQuery = true, value = getOrderList)
    List<Integer> getOrders(int userId);
}
