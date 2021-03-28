package Distribution.API.base.Service;

import Distribution.API.base.Model.Account;
import Distribution.API.base.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public Account getAccountById(int id) {
        return accountRepo.getAccountById(id);
    }
//
//    public Account getAccountByEmail(String email) {
//        return accountRepo.getAcountByEmail(email);
//    }
//
//    public Boolean changePass(String password) {
//        return accountRepo.changePassword(password);
//    }

}
