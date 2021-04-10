package Distribution.API.base.Controller.User;

import Distribution.API.base.Model.Account;
import Distribution.API.base.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/user/account")
public class UserAccountController {

    @Autowired
    private AccountService accountSrv;

    @RequestMapping( value = "/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity getClientAccount(@PathVariable("clientId") int clientId)  {
        Account account = accountSrv.getAccountById(clientId);
        if (account==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

//    @RequestMapping( value = "/user", method = RequestMethod.GET)
//    public ResponseEntity getAccount(@RequestParam("email") String email)  {
//        Account account = accountSrv.getAccountByEmail(email);
//        if (account==null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(account);
//    }
//
//    @RequestMapping( value = "/user", method = RequestMethod.PUT)
//    public ResponseEntity updateAccount(@RequestBody Account updAccount)  {
//        Account account = accountSrv.getAccountByEmail(updAccount.getEmail());
//        if (account==null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(account);
//    }
//
//    @RequestMapping( value = "/user", method = RequestMethod.PUT)
//    public ResponseEntity updatePass(@RequestParam("pass") String password)  {
//        accountSrv.changePass(password);
//        return ResponseEntity.ok().build();
//    }
}
