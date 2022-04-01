package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;
import de.egga.designamusement.shops.OrderSystem;
import de.egga.designamusement.shops.Shop;
import de.egga.designamusement.shops.Item;

public class Restaurant extends Shop {

    public Restaurant(Location location, OrderSystem orderSystem) {
        super(location, orderSystem);
    }

    @Override
    public void order(Item... items) {
        orderSystem.order(items);
    }
}
