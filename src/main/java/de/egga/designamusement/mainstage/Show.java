package de.egga.designamusement.mainstage;

import java.time.LocalTime;
import java.util.UUID;

public class Show {

    private final UUID id;
    private final int price;
    private final Audience audience;

    public Show(UUID id, String title, LocalTime afternoon, int price, Audience audience) {
        this.id = id;
        this.price = price;
        this.audience = audience;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        return id.equals(show.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Audience getAudience() {
        return audience;
    }
}
