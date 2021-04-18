package Distribution.API.base.Controller.User;

import Distribution.API.base.Exceptions.CheckObject;
import Distribution.API.base.Model.Account;
import Distribution.API.base.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/user/account")
public class UserAccountController {

    @Autowired
    private AccountService accountSrv;
    @Autowired
    private CheckObject check;

    @GetMapping( value = "/{user}" )
    public ResponseEntity getClientAccount(@PathVariable("user") String user)  {
        Account account = accountSrv.getAccountByEmail(user);
        check.checkIfNull(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping( value = "/")
    public ResponseEntity getClientAccount(@RequestBody Account account)  {
        account = accountSrv.setAccountByEmail(account);
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
