package de.egga.designamusement.shops.nonfood;

import java.util.ArrayList;
import java.util.List;

public class NonFoodShop {

    private final StoreRepository repository;

    public NonFoodShop(StoreRepository repository) {
        this.repository = repository;
    }

    public boolean isInStock(Item item) {
        return repository.isInStock(item);
    }

    public List<Item> getItemsOf(ItemTypes type) {
        List<Item> result = new ArrayList<>();

        for (Item item : repository.fetchAllItems()) {
            if (item.getType().equals(type)) {
                result.add(item);
            }
        }

        return result;
    }
}
