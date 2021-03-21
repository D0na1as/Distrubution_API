package Distribution.API.base.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;

@Repository
public interface OrderRepo extends CrudRepository<Integer, Long> {
}
