package Distribution.API.base.Controller.Client;

import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/client/storage")
public class ClientStorageService {

    @Autowired
    private StorageService storageSrv;

    @RequestMapping( value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity getPage(@PathVariable("page") int page,
                                  @RequestParam("count") int count)  {
        List<Item> items = storageSrv.getPage(page, count);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @RequestMapping( value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity getItem(@PathVariable int id)  {
        Item item = storageSrv.getItem(id);
        if (item.getId()==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

}
