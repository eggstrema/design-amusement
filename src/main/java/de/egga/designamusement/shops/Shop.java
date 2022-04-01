package de.egga.designamusement.shops;

public abstract class Shop {

    // opening times
    // cashier report
    // deals

    final Location location;
    protected final OrderSystem orderSystem;

    public Shop(Location location, OrderSystem orderSystem) {
        this.location = location;
        this.orderSystem = orderSystem;
    }

    public abstract void order(Item... items);

    public Location getLocation() {
        return location;
    }
}
