package de.egga.designamusement.mainstage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static de.egga.designamusement.mainstage.Audience.*;
import static de.egga.designamusement.mainstage.ShowTest.*;
import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ISO_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainStageTest {

    @Mock
    MainStageRepository repository;

    @Mock
    Clock clock;

    @InjectMocks
    MainStage stage;

    @Test
    void new_show_is_contained_in_list_of_all_shows() {
        Show newShow = anyShow();
        stage.add(newShow);
        assertThat(stage.listAllShows()).contains(newShow);
    }

    @Test
    void new_show_is_persisted_in_repository() {
        Show newShow = anyShow();
        stage.add(newShow);
        verify(repository).save(newShow);
    }

    @Test
    void list_all_shows_fetches_updates_from_repository() {
        Show show = anyShow();
        when(repository.receive()).thenReturn(List.of(show));
        assertThat(stage.listAllShows()).contains(show);
    }

    @Test
    void too_expensive_shows_are_filtered_out() {
        Show regularShow = showFor(20);
        Show cheapShow = showFor(2);
        Show expensiveShow = showFor(200);
        when(repository.receive()).thenReturn(List.of(regularShow, cheapShow, expensiveShow));
        assertThat(stage.listShowsBelow(50)).containsOnly(regularShow, cheapShow);
    }

    @Test
    void not_suitable_shows_are_filtered_out() {
        Show familyShow = showFor(FAMILIES_WITH_CHILDREN);
        Show explicitShow = showFor(EXPLICIT_CONTENT);
        Show fanShow = showFor(WHOVIANS);
        when(repository.receive()).thenReturn(List.of(familyShow, explicitShow, fanShow));
        assertThat(stage.listShowsFor(WHOVIANS)).containsOnly(fanShow);
    }

    @Test
    void returns_morning_show_in_the_morning() {
        Show morningShow = showAt("10:00:00");
        Show noonShow = showAt("13:00:00");
        Show primeTimeShow = showAt("20:00:00");
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("08:00:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(morningShow);
    }

    @Test
    void returns_noon_show_later() {
        Show morningShow = showAt("10:00:00");
        Show noonShow = showAt("13:00:00");
        Show primeTimeShow = showAt("20:00:00");
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("12:55:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(noonShow);
    }

    @Test
    void returns_prime_time_show_in_the_evening() {
        Show morningShow = showAt("10:00:00");
        Show noonShow = showAt("13:00:00");
        Show primeTimeShow = showAt("20:00:00");
        when(repository.receive()).thenReturn(List.of(morningShow, noonShow, primeTimeShow));

        when(clock.now()).thenReturn(parse("18:00:00", ISO_TIME));

        assertThat(stage.getNextShow()).isEqualTo(primeTimeShow);
    }


}
