package Distribution.API.base.Repository;

import Distribution.API.base.Model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    String table = "account";

    //Queries
    String getAccount = "SELECT * FROM "+ table +" WHERE id=?";

    //Query execution
    @Query(nativeQuery = true, value = getAccount)
    Account getAccountById(int id);

}
