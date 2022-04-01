package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static de.egga.designamusement.shops.restaurants.RestaurantTypes.SWEDISH_CHEF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    static final Menu ANY_MENU = new Menu();

    @Mock
    RestaurantProvider restaurantProvider;

    @Mock
    MenuProvider menuProvider;

    @InjectMocks
    RestaurantService service;

    @Test
    void retrieves_menu_from_menu_provider() {
        when(menuProvider.getMenu(SWEDISH_CHEF)).thenReturn(ANY_MENU);
        Menu returnedMenu = service.getMenu(SWEDISH_CHEF);
        assertThat(returnedMenu).isEqualTo(ANY_MENU);
    }

    @Test
    void finds_closest_restaurant() {
        Location myLocation = new Location(1, 2);
        Restaurant farAwayRestaurant = new Restaurant(new Location(12, 13));
        Restaurant someRestaurant = new Restaurant(new Location(5, 6));
        Restaurant closeRestaurant = new Restaurant(new Location(2, 1));
        when(restaurantProvider.fetchAllRestaurants()).thenReturn(List.of(farAwayRestaurant, someRestaurant, closeRestaurant));

        Restaurant foundRestaurant = service.findClosestRestaurant(myLocation);
        assertThat(foundRestaurant).isEqualTo(closeRestaurant);
    }
}
