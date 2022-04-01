package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;
import de.egga.designamusement.shops.OrderSystem;

import java.util.List;

import static java.lang.Math.abs;

public class RestaurantService {

    private final RestaurantProvider restaurantProvider;
    private final MenuProvider menuProvider;
    private final OrderSystem orderSystem;

    public RestaurantService(RestaurantProvider restaurantProvider, MenuProvider menuProvider, OrderSystem orderSystem) {
        this.restaurantProvider = restaurantProvider;
        this.menuProvider = menuProvider;
        this.orderSystem = orderSystem;
    }

    public Menu getMenu(RestaurantTypes restaurant) {
        return menuProvider.getMenu(restaurant);
    }

    public Restaurant findClosestRestaurant(Location location) {
        List<Restaurant> restaurants = restaurantProvider.fetchAllRestaurants();

        Restaurant closestRestaurant = null;

        for (Restaurant restaurant : restaurants) {
            if (closestRestaurant == null) {
                closestRestaurant = restaurant;
            } else {
                int distance = distanceBetween(restaurant.getLocation(), location);
                int closestDistance = distanceBetween(closestRestaurant.getLocation(), location);
                if (distance < closestDistance) {
                    closestRestaurant = restaurant;
                }
            }
        }

        return closestRestaurant;
    }

    private int distanceBetween(Location thisLocation, Location thatLocation) {
        // we consider people to be moving on a grid, where
        // diagonal movements are not possible due to obstacles
        int distanceX = abs(thisLocation.getX() - thatLocation.getX());
        int distanceY = abs(thisLocation.getY() - thatLocation.getY());
        return distanceX + distanceY;
    }
}
