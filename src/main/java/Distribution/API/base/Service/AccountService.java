package Distribution.API.base.Service;

<<<<<<< HEAD
=======
import Distribution.API.base.Model.Account;
import Distribution.API.base.Repository.AccountRepo;
import Distribution.API.base.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>>  commit
import org.springframework.stereotype.Service;

@Service
public class AccountService {
<<<<<<< HEAD
=======

    @Autowired
    private AccountRepo accountRepo;

    public Account getAccountById(int id) {
        return accountRepo.getAcountById(id);
    }

    public Account getAccountByEmail(String email) {
        return accountRepo.getAcountByEmail(email);
    }

    public Boolean changePass(String password) {
        return accountRepo.changePassword(password);
    }
>>>>>>>  commit
}
