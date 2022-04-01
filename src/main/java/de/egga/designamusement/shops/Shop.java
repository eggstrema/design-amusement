package de.egga.designamusement.shops;

public class Shop {

    protected final Location location;

    public Shop(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
