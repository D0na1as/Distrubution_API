package Distribution.API.base.Controller.User;

import Distribution.API.base.Model.Account;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/user/storage")
public class AccountController {

    @Autowired
    private AccountService accountSrv;

    @RequestMapping( value = "/user", method = RequestMethod.GET)
    public ResponseEntity getAccount(@RequestParam("email") String email)  {
        Account account = accountSrv.getAccount(email);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @RequestMapping( value = "/user", method = RequestMethod.PUT)
    public ResponseEntity updateAccount(@RequestBody Account updAccount)  {
        Account account = accountSrv.getAccount(updAccount.getEmail());
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

}
