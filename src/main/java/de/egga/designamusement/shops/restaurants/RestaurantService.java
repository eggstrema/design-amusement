package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;

import java.util.List;

import static java.lang.Math.abs;

public class RestaurantService {

    private final RestaurantProvider restaurantProvider;
    private final MenuProvider menuProvider;

    public RestaurantService(RestaurantProvider restaurantProvider, MenuProvider menuProvider) {
        this.restaurantProvider = restaurantProvider;
        this.menuProvider = menuProvider;
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
        int distanceX = abs(thisLocation.x() - thatLocation.x());
        int distanceY = abs(thisLocation.y() - thatLocation.y());
        return distanceX + distanceY;
    }
}
