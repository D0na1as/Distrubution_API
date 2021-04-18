package Distribution.API.base.Controller.User;

import Distribution.API.base.Exceptions.CheckObject;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
        //check.checkItemEmpty(items);
        return ResponseEntity.ok(items);
    }


    @GetMapping( "/item/{id}" )
    public ResponseEntity getItem(@PathVariable long id) {

        Item item = storageSrv.getItem(id);
        check.checkIfNull(item);
        return ResponseEntity.ok(item);
    }

    @PostMapping( value = "/item")
    public ResponseEntity addItem(@RequestBody Item item,
                                  RedirectAttributes redirectAttributes)  {

        Item itmBySerial = storageSrv.getBySerial(item.getSerial());
        if (item.getSerial().equals(itmBySerial.getSerial())) {
            item.setId(itmBySerial.getId());
            return ResponseEntity.ok(storageSrv.updateItem(item));
        }
        return ResponseEntity.ok(storageSrv.addItem(item);
    }

    @PutMapping( value = "/item/{id}" )
    public ResponseEntity updateItem(@RequestBody Item item,
                                     @PathVariable long id)  {
        Item itmById = storageSrv.getItem(id);
        Item itmBySerial = storageSrv.getBySerial(item.getSerial());
        check.checkIfNull(itmById);
        check.checkPutCount(item.getQuantity());
        if (itmById!=itmBySerial) {
            throw new ResponseStatusException(BAD_REQUEST,"Duplicate serial!");
        }
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