package de.egga.designamusement.shops;

public abstract class Shop {

    // opening times
    // cashier report
    // deals

    final Location location;

    public Shop(Location location) {
        this.location = location;
    }

    public abstract void order(Item... items);

    public Location getLocation() {
        return location;
    }
}
