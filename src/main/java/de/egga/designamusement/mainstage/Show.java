package de.egga.designamusement.mainstage;

import java.time.LocalTime;
import java.util.UUID;

public class Show {

    private final UUID id;
    private final String title;
    private final LocalTime time;
    private final int price;
    private final Audience audience;

    public Show(UUID id, String title, LocalTime time, int price, Audience audience) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.price = price;
        this.audience = audience;
    }

    public int getPrice() {
        return price;
    }

    public Audience getAudience() {
        return audience;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", price=" + price +
                ", audience=" + audience +
                '}';
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
}
