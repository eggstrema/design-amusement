package de.egga.designamusement.shops;

import static de.egga.designamusement.shops.ItemTypes.CLOTHING;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    public static final Item ANY_ITEM = new Item(randomUUID(), "any title", CLOTHING, 123);
}