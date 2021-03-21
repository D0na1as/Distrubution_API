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
        if (item==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(storageSrv.addItem(item));
    }

    @RequestMapping( value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity getPage(@PathVariable("page") int page,
                                  @RequestParam("value") int count)  {
        List<Item> items = storageSrv.getPage(page, count);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @RequestMapping( value = "/item/count", method = RequestMethod.GET)
    public ResponseEntity getStorageSize()  {
        long count = storageSrv.getCount();
        if (count!=0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(count);
    }

    @RequestMapping( value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity getItem(@PathVariable long id)  {
        Item item = storageSrv.getItem(id);
        if (item==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @RequestMapping( value = "/item", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@RequestBody Item item)  {
        Item checkItm = storageSrv.getItem(item.getId());
        if (item==null || checkItm==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(storageSrv.updateItem(item));
    }
}