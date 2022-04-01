package de.egga.designamusement.mainstage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static de.egga.designamusement.mainstage.Audience.*;
import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ISO_TIME;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainStageTest {

    static final LocalTime ANY_TIME = parse("16:00:00", ISO_TIME);
    static final String ANY_TITLE = "Julia & Oscar the Grouch";
    static final int ANY_PRICE = 20;
    static final Audience ANY_AUDIENCE = WHOVIANS;

    @Mock
    MainStageRepository repository;

    @Mock
    Clock clock;

    @InjectMocks
    MainStage stage;

    @Test
    void new_show_is_contained_in_list_of_all_shows() {
        Show newShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, ANY_AUDIENCE);
        stage.add(newShow);
        assertThat(stage.listAllShows()).contains(newShow);
    }

    @Test
    void new_show_is_persisted_in_repository() {
        Show newShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, ANY_AUDIENCE);
        stage.add(newShow);
        verify(repository).save(newShow);
    }

    @Test
    void list_all_shows_fetches_updates_from_repository() {
        Show show = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, ANY_AUDIENCE);
        when(repository.receive()).thenReturn(List.of(show));
        assertThat(stage.listAllShows()).contains(show);
    }

    @Test
    void too_expensive_shows_are_filtered_out() {
        Show regularShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, 20, ANY_AUDIENCE);
        Show cheapShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, 2, ANY_AUDIENCE);
        Show expensiveShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, 200, ANY_AUDIENCE);
        when(repository.receive()).thenReturn(List.of(regularShow, cheapShow, expensiveShow));
        assertThat(stage.listShowsBelow(50)).containsOnly(regularShow, cheapShow);
    }

    @Test
    void not_suitable_shows_are_filtered_out() {
        Show familyShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, FAMILIES_WITH_CHILDREN);
        Show explicitShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, EXPLICIT_CONTENT);
        Show fanShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, ANY_PRICE, WHOVIANS);
        when(repository.receive()).thenReturn(List.of(familyShow, explicitShow, fanShow));
        assertThat(stage.listShowsFor(WHOVIANS)).containsOnly(fanShow);
    }

    @Test
    void returns_morning_show_in_the_morning() {
        Show morningShow = new Show(randomUUID(), ANY_TITLE, parse("10:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show noonShow = new Show(randomUUID(), ANY_TITLE, parse("13:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show primeTimeShow = new Show(randomUUID(), ANY_TITLE, parse("20:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("08:00:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(morningShow);
    }

    @Test
    void returns_noon_show_later() {
        Show morningShow = new Show(randomUUID(), ANY_TITLE, parse("10:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show noonShow = new Show(randomUUID(), ANY_TITLE, parse("13:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show primeTimeShow = new Show(randomUUID(), ANY_TITLE, parse("20:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("12:55:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(noonShow);
    }

    @Test
    void returns_prime_time_show_in_the_evening() {
        Show morningShow = new Show(randomUUID(), ANY_TITLE, parse("10:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show noonShow = new Show(randomUUID(), ANY_TITLE, parse("13:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        Show primeTimeShow = new Show(randomUUID(), ANY_TITLE, parse("20:00:00", ISO_TIME), ANY_PRICE, ANY_AUDIENCE);
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("18:00:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(primeTimeShow);
    }

}
