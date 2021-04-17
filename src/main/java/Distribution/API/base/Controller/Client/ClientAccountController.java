package Distribution.API.base.Controller.Client;

import Distribution.API.base.Controller.Exceptions.CheckObject;
import Distribution.API.base.Model.Account;
import Distribution.API.base.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/client/account")
public class ClientAccountController {

    @Autowired
    private AccountService accountSrv;
    @Autowired
    private CheckObject check;

    @GetMapping( value = "/{client}" )
    public ResponseEntity getClientAccount(@PathVariable("client") String client)  {
        Account account = accountSrv.getAccountByEmail(client);
        check.checkIfNull(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping( value = "/")
    public ResponseEntity getClientAccount(@RequestBody Account account)  {
        account = accountSrv.setAccountByEmail(account);
        return ResponseEntity.ok(account);
    }

//    @RequestMapping( value = "/", method = RequestMethod.PUT)
//    public ResponseEntity updatePass(@RequestParam("pass") String password)  {
//        accountSrv.changePass(password);
//        return ResponseEntity.ok().build();
//    }
}
