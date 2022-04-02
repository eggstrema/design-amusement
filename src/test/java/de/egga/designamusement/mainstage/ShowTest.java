package de.egga.designamusement.mainstage;

import java.time.LocalTime;

import static de.egga.designamusement.mainstage.Audience.WHOVIANS;
import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ISO_TIME;

public class ShowTest {

    public static final String ANY_TITLE = "Julia & Oscar the Grouch";
    public static final LocalTime ANY_TIME = parse("16:00:00", ISO_TIME);
    public static final int ANY_PRICE = 20;
    public static final Audience ANY_AUDIENCE = WHOVIANS;

}
