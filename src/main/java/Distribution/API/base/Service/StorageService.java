package Distribution.API.base.Service;

import Distribution.API.base.Model.Item;
import Distribution.API.base.Repository.StorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageRepo storageRepo;

    public Item addItem(Item item) {
        return storageRepo.save(item);
    }

    public List<Item> getPage(int page, int count) {
        long allItemCount = getCount();
        if ( allItemCount >= ((long) page * count ) ) {
            int first = page * count - count;
            int last = page * count;
            List<Item> items = storageRepo.getPageItems(first, last);
        } else if ( allItemCount < ((long) page * count ) && allItemCount > ((long) ((long) (page - 1) * count))) {
            int first = page * count - count;
            List<Item> items = storageRepo.getPageItems(first, allItemCount);
        }

        return new ArrayList<Item>();
    }

    public long getCount() {
        return storageRepo.storageItemCount();
    }

    public Item getItem(long id) {
        return storageRepo.findById(id).orElse(new Item());
    }

    public Item updateItem(Item item) {
        return addItem(item);
    }

    public List<Integer> getOrders(int userId) {
        return storageRepo.getOrders(userId);
    }

    public List<Item> getOrder(long orderId) {
        return storageRepo.getOrder(orderId);
    }
}
