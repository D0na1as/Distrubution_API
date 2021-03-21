package Distribution.API.base.Repository;

import Distribution.API.base.Model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends CrudRepository<Item, Long> {

}
