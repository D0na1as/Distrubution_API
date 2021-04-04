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
        if ( allItemCount > 0 ) {
            long first = (long) page * count - count;
            return storageRepo.getPageItems(first, count);
        }
//        else if ( allItemCount < ((long) page * count ) && allItemCount > (((long) (page - 1) * count))) {
//            long first = (long) page * count - count;
//            List<Item> items = storageRepo.getPageItems(first, count);
//            return items;
//        }
        return new ArrayList<>();
    }

    public List<Item> getPage(int page, int count, String search) {
        long allItemCount = getCount(search);
        if ( allItemCount > 0 ) {
            long first = (long) page * count - count;
            return storageRepo.getSearchPage(first, count, search);
        }
        return new ArrayList<>();
    }

    public long getCount() {
        return storageRepo.storageItemCount();
    }

    public long getCount(String search) {
        return storageRepo.storageItemCount(search);
    }

    public Item getItem(long id) {
        return storageRepo.findById(id).orElse(new Item());
    }

    public Item updateItem(Item item) {
        return addItem(item);
    }

    public List<Long> getOrders(int userId) {
        return storageRepo.getOrders(userId);
    }

    public List<Item> getOrderItems(long orderId) {
        return storageRepo.getOrderItems(orderId);
    }
}
