package de.egga.designamusement.shops;

import static de.egga.designamusement.shops.ItemTypes.CLOTHING;
import static java.util.UUID.randomUUID;

public class ItemTest {

    public static final Item ANY_ITEM = new Item(randomUUID(), "any title", CLOTHING, 123);

    public static Item item(String title, ItemTypes type) {
        return new Item(randomUUID(), title, type, 25);
    }
}