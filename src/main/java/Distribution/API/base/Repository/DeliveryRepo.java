package Distribution.API.base.Repository;

import Distribution.API.base.Config.OrderStatus;
import Distribution.API.base.Model.Delivery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DeliveryRepo extends CrudRepository<Delivery, Long> {

    String table = "deliveries";

    //Queries
    String getDeliveryByStatus = "SELECT * FROM `"+ table +"` WHERE status=:#{#status.name()}";
    String getByStatusAndClient = "SELECT * FROM `"+ table +"` WHERE `status`=':#{#status.name()}' AND `client`=':client'";
    String updateStatus = "UPDATE `" + table + "` SET status=:#{#status.name()} WHERE id=?1";
    String getClient = "SELECT IFNULL((SELECT client FROM `"+ table +"` WHERE id=?1), 0);";

    @Query(nativeQuery = true, value = getDeliveryByStatus)
    List<Delivery> getByStatus(@Param("status") OrderStatus status);

    @Query(nativeQuery = true, value = getClient)
    String getClientByOrder(long orderId);

    @Query(nativeQuery = true, value = getByStatusAndClient)
    List<Delivery> getByClientAndStatus(@Param("client")String client, @Param("status") OrderStatus status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = updateStatus)
    void setStatus(long orderId, @Param("status") OrderStatus status);

}
