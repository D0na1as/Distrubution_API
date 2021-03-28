package Distribution.API.base.Controller.Client;

import Distribution.API.base.Model.Account;
import Distribution.API.base.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/client/account")
public class ClientAccountController {

    @Autowired
    private AccountService accountSrv;
//
//    @RequestMapping( value = "/", method = RequestMethod.GET)
//    public ResponseEntity getAccount(@RequestParam("email") String email)  {
//        Account account = accountSrv.getAccountByEmail(email);
//        if (account==null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(account);
//    }
//
//    @RequestMapping( value = "/", method = RequestMethod.PUT)
//    public ResponseEntity updateAccount(@RequestBody Account updAccount)  {
//        Account account = accountSrv.getAccountByEmail(updAccount.getEmail());
//        if (account==null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(account);
//    }
//
//    @RequestMapping( value = "/", method = RequestMethod.PUT)
//    public ResponseEntity updatePass(@RequestParam("pass") String password)  {
//        accountSrv.changePass(password);
//        return ResponseEntity.ok().build();
//    }
}
