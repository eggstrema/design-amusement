package de.egga.designamusement.shops.nonfood;

import de.egga.designamusement.shops.Item;
import de.egga.designamusement.shops.ItemTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static de.egga.designamusement.shops.ItemTest.ANY_ITEM;
import static de.egga.designamusement.shops.ItemTest.item;
import static de.egga.designamusement.shops.ItemTypes.APPAREL;
import static de.egga.designamusement.shops.ItemTypes.CLOTHING;
import static de.egga.designamusement.shops.nonfood.NonFoodShopTest.ANY_SHOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NonFoodShopServiceTest {

    @Mock
    StoreRepository repository;

    @InjectMocks
    NonFoodShopService service;

    @Test
    void inStockItemsAreReported() {
        when(repository.isInStock(ANY_SHOP, ANY_ITEM)).thenReturn(true);
        assertThat(service.isInStock(ANY_SHOP, ANY_ITEM)).isTrue();
    }

    @Test
    void soldOutItemsAreReported() {
        when(repository.isInStock(ANY_SHOP, ANY_ITEM)).thenReturn(false);
        assertThat(service.isInStock(ANY_SHOP, ANY_ITEM)).isFalse();
    }

    @Test
    void returns_only_items_of_given_type() {
        Item shirt = item("Big Bird Shirt", CLOTHING);
        Item wand = item("Abby Cadabby Wand", APPAREL);
        Item onesie = item("Elmo Onesies", CLOTHING);
        Item monocle = item("Count von Count Monocle", APPAREL);
        Item dress = item("Prairie Dawn Dress", CLOTHING);

        when(repository.fetchAllItems(ANY_SHOP)).thenReturn(List.of(shirt, wand, onesie, monocle, dress));
        assertThat(service.getItemsOf(ANY_SHOP, CLOTHING)).containsExactly(shirt, onesie, dress);
    }

}