package Distribution.API.base.Repository;

import Distribution.API.base.Model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepo extends CrudRepository<Item, Long> {

    String storage = "storage";
    String orders = "orders";

    //Queries
    String getBySerial = "SELECT * FROM "+ storage +" WHERE `serial`=?";
    String entitiesCount = "SELECT IFNULL((SELECT COUNT(*) FROM "+ storage +"), 0)";
    String searchCount = "SELECT IFNULL((SELECT COUNT(*) FROM `"+ storage +"` WHERE `id` LIKE %:value% OR `title` LIKE %:value% OR " +
                         "`serial` LIKE %:value% OR `quantity` LIKE %:value%),0)";
    String searchPage = "SELECT * FROM `"+ storage +"` WHERE `id` LIKE %:value% OR `title` LIKE %:value% OR " +
                        "`serial` LIKE %:value% OR `quantity` LIKE %:value% LIMIT :first,:count";
    String pageItems = "SELECT * FROM "+ storage +" LIMIT ?1,?2";
    String getOrderItems = "SELECT "+ storage +".id, `title`, `serial`, "+ orders +".quantity FROM `"+ storage +"` " +
                           "RIGHT JOIN `"+ orders +"` ON "+ storage +".id="+ orders +".item_id WHERE "+ orders +".order_id=?";

    //Query execution
    @Query(nativeQuery = true, value = entitiesCount)
    long storageItemCount();

    @Query(nativeQuery = true, value = searchCount)
    long storageItemCount(@Param("value") String value);

    @Query(nativeQuery = true, value = pageItems)
    List<Item> getPageItems(long first, long count);

    @Query(nativeQuery = true, value = searchPage)
    List<Item> getSearchPage(@Param("first")  long first, @Param("count") long count, @Param("value") String value);

    @Query(nativeQuery = true, value = getOrderItems)
    List<Item> getOrderItems(long orderId);

    @Query(nativeQuery = true, value = getBySerial)
    Item getBySerial(String serial);
//
//    @Query(nativeQuery = true, value = getOrderListByUser)
//    List<Long> getOrders(int userId);

    //Add item to specific department
//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = addItem)
//    Item addItem(Item item);


}
