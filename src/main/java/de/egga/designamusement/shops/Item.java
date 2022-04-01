package de.egga.designamusement.shops;

import java.util.UUID;

public class Item {

    private final UUID id;
    private final String title;
    private final ItemTypes type;
    private final int price;

    public Item(UUID id, String title, ItemTypes type, int price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.price = price;
    }

    public ItemTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getTitle() {
        return title;
    }
}
