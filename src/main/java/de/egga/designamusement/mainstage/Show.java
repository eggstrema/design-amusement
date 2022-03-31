package de.egga.designamusement.mainstage;

import java.time.LocalTime;

public class Show {

    private final int price;

    public Show(String title, LocalTime afternoon, int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
