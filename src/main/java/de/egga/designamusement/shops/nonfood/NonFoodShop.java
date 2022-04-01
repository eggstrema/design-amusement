package de.egga.designamusement.shops.nonfood;

import de.egga.designamusement.shops.Item;
import de.egga.designamusement.shops.Location;
import de.egga.designamusement.shops.Shop;

public class NonFoodShop extends Shop {

    public NonFoodShop(Location location) {
        super(location, null);
    }

    @Override
    public void order(Item... items) {
        throw new CommandNotSupportedException();
    }
}
