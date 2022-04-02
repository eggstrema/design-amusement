package de.egga.designamusement.mainstage;

import java.time.LocalTime;

import static de.egga.designamusement.mainstage.Audience.WHOVIANS;
import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ISO_TIME;
import static java.util.UUID.randomUUID;

public class ShowTest {

    public static final String ANY_TITLE = "Julia & Oscar the Grouch";
    public static final LocalTime ANY_TIME = parse("16:00:00", ISO_TIME);
    public static final int ANY_PRICE = 20;
    public static final Audience ANY_AUDIENCE = WHOVIANS;

     public static Show anyShow() {
        return new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, ANY_AUDIENCE);
    }

     public static Show showAt(String time) {
        return new Show(randomUUID(), ANY_TITLE, parse(time, ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
    }

     public static Show showFor(int price) {
        return new Show(randomUUID(), ANY_TITLE, ANY_TIME, price, ANY_AUDIENCE);
    }

     public static Show showFor(Audience audience) {
        return new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, audience);
    }
}
