package Distribution.API.base.Controller.User;

import Distribution.API.base.Controller.Exceptions.CheckObject;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.StorageService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin
@RequestMapping("/v1/user/storage")
public class UserStorageController {

    @Autowired
    private StorageService storageSrv;
    @Autowired
    private CheckObject check;


    @GetMapping( "/page/{page}" )
    public ResponseEntity getPage(@PathVariable("page") int page,
                                  @RequestParam("count") int count) {
        List<Item> items = storageSrv.getPage(page, count);
        check.checkItemEmpty(items);
        return ResponseEntity.ok(items);
    }


    @GetMapping( "/item/{id}" )
    public ResponseEntity getItem(@PathVariable long id) throws Exception {

        Item item = storageSrv.getItem(id);
        check.checkIfNull(item);
        return ResponseEntity.ok(item);
    }

    @PostMapping( value = "/item")
    public ResponseEntity addItem(@RequestBody Item item)  {
        //TODO check for empty object, check same object
        Item newItem;
        try {
            newItem = storageSrv.addItem(item);
        } catch(DataIntegrityViolationException e) {
            throw new ResponseStatusException(BAD_REQUEST,"Duplicate serial!");
        }
        return ResponseEntity.ok(newItem);
    }

    @PutMapping( value = "/item/{id}" )
    public ResponseEntity updateItem(@RequestBody Item item,
                                     @PathVariable long id)  {
        //TODO check if item exists
        Item checkItm = storageSrv.getItem(id);
        check.checkById(checkItm.getId());
        return ResponseEntity.ok(storageSrv.updateItem(item));
    }

    @DeleteMapping( value = "/item/{id}" )
    public ResponseEntity deleteItem(@PathVariable long id)  {
        Item checkItm = storageSrv.getItem(id);
        check.checkById(checkItm.getId());
        storageSrv.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping( value = "/item/count" )
    public ResponseEntity getStorageSize()  {
        long count = storageSrv.getCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping( value = "/page/{search}/{page}" )
    public ResponseEntity getSearchPage(@PathVariable("search") String search,
                                        @PathVariable("page") int page,
                                        @RequestParam("count") int count)  {
        List<Item> items = storageSrv.getPage(page, count, search);
        check.checkItemEmpty(items);
        return ResponseEntity.ok(items);
    }

    @GetMapping( value = "/item/count/{search}" )
    public ResponseEntity getStorageSearch(@PathVariable("search") String search){
        long count = storageSrv.getCount(search);
        return ResponseEntity.ok(count);
    }
}