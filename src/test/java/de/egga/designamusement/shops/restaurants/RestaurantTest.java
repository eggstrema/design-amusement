package de.egga.designamusement.shops.restaurants;

import de.egga.designamusement.shops.Location;
import de.egga.designamusement.shops.OrderSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.egga.designamusement.shops.ItemTest.ANY_ITEM;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    @Mock
    Location location;

    @Mock
    OrderSystem orderSystem;

    @InjectMocks
    Restaurant restaurant;

    @Test
    void order_should_be_passed_on() {
        restaurant.order(ANY_ITEM);
        verify(orderSystem).order(ANY_ITEM);
    }
}