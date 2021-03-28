package Distribution.API.base.Controller.User;

import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/user/storage")
public class UserStorageController {

    @Autowired
    private StorageService storageSrv;

    @RequestMapping( value = "/item", method = RequestMethod.POST)
    public ResponseEntity addItem(@RequestBody Item item)  {
        Item asd = item;
        //TODO check for empty object
        if (item==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(storageSrv.addItem(item));
    }

    @RequestMapping( value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity getPage(@PathVariable("page") int page,
                                  @RequestParam("count") int count)  {
        List<Item> items = storageSrv.getPage(page, count);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @RequestMapping( value = "/item/count", method = RequestMethod.GET)
    public ResponseEntity getStorageSize()  {
        long count = storageSrv.getCount();
        if (count==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(count);
    }

    @RequestMapping( value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity getItem(@PathVariable long id)  {
        Item item = storageSrv.getItem(id);
        if (item.getId()==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @RequestMapping( value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@RequestBody Item item,
                                     @PathVariable long id)  {
        Item checkItm = storageSrv.getItem(id);
        if (checkItm.getId()==0) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        return ResponseEntity.ok(storageSrv.updateItem(item));
    }
}