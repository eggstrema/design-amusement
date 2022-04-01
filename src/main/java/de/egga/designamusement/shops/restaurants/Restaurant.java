package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;

public class Restaurant {

    private final Location location;

    public Restaurant(Location location) {

        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
