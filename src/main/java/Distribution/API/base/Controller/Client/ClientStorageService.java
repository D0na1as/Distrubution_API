package Distribution.API.base.Controller.Client;

import Distribution.API.base.Exceptions.CheckObject;
import Distribution.API.base.Model.Item;
import Distribution.API.base.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1/client/storage")
public class ClientStorageService {

    @Autowired
    private StorageService storageSrv;
    @Autowired
    private CheckObject check;

    @GetMapping( "/page/{page}" )
    public ResponseEntity getPage(@PathVariable("page") int page,
                                  @RequestParam("count") int count)  {
        List<Item> items = storageSrv.getPage(page, count);
        //check.checkItemEmpty(items);
//        if (items.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(items);
    }

    @GetMapping( "/item/{id}" )
    public ResponseEntity getItem(@PathVariable long id)  {
        //TODO catch null
        Item item = storageSrv.getItem(id);
        check.checkById(item.getId());
//        if (item.getId()==0) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(item);
    }

    @GetMapping( value = "/item/count" )
    public ResponseEntity getStorageSize()  {
        long count = storageSrv.getCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping( value = "/item/count/{search}" )
    public ResponseEntity getStorageSearch(@PathVariable("search") String search){
        long count = storageSrv.getCount(search);
        return ResponseEntity.ok(count);
    }


}
