package de.egga.designamusement.shops.nonfood;

import de.egga.designamusement.shops.Item;
import de.egga.designamusement.shops.ItemTypes;

import java.util.ArrayList;
import java.util.List;

public class NonFoodShopService {

    private final StoreRepository repository;

    public NonFoodShopService(StoreRepository repository) {
        this.repository = repository;
    }

    public boolean isInStock(NonFoodShop shop, Item item) {
        return repository.isInStock(shop, item);
    }

    public List<Item> getItemsOf(NonFoodShop shop, ItemTypes type) {
        List<Item> result = new ArrayList<>();

        for (Item item : repository.fetchAllItems(shop)) {
            if (item.getType().equals(type)) {
                result.add(item);
            }
        }

        return result;
    }

}
