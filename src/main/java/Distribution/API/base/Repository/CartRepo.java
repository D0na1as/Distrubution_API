package Distribution.API.base.Repository;

import Distribution.API.base.Model.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {

    String table = "cart";

    //Queries
    String getCart = "SELECT * FROM "+ table ;
    String getItemId = "SELECT IFNULL((SELECT id FROM "+ table +" WHERE item_id=?), 0) " ;

    //Query execution
    @Query(nativeQuery = true, value = getCart)
    List<Cart> getCart();

    @Query(nativeQuery = true, value = getItemId)
    long getItemId(long id);

}