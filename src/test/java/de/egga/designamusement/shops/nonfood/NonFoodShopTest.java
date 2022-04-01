package de.egga.designamusement.shops.nonfood;

import de.egga.designamusement.shops.Location;
import org.junit.jupiter.api.Test;

import static de.egga.designamusement.shops.ItemTest.ANY_ITEM;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NonFoodShopTest {

    static final NonFoodShop ANY_SHOP = new NonFoodShop(new Location(0,0));

    @Test
    void should_throw_exception_if_tried_to_order_online() {
        assertThatThrownBy(() -> {
            ANY_SHOP.order(ANY_ITEM);
        }).isInstanceOf(CommandNotSupportedException.class);
    }
}