package de.egga.designamusement.mainstage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ISO_TIME;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainStageTest {

    public static final LocalTime ANY_TIME = parse("16:00:00", ISO_TIME);
    public static final String ANY_TITLE = "Julia & Oscar the Grouch";
    public static final int ANY_PRICE = 20;
    public static final UUID ANY_UUID = randomUUID();

    @Mock
    MainStageRepository repository;

    @InjectMocks
    MainStage stage;

    @Test
    void new_show_is_contained_in_list_of_all_shows() {
        Show newShow = new Show(ANY_UUID, ANY_TITLE, ANY_TIME, ANY_PRICE);
        stage.add(newShow);
        assertThat(stage.listAllShows()).contains(newShow);
    }

    @Test
    void new_show_is_persisted_in_repository() {
        Show newShow = new Show(ANY_UUID, ANY_TITLE, ANY_TIME, ANY_PRICE);
        stage.add(newShow);
        verify(repository).save(newShow);
    }

    @Test
    void list_all_shows_fetches_updates_from_repository() {
        Show show = new Show(ANY_UUID, ANY_TITLE, ANY_TIME, ANY_PRICE);
        when(repository.receive()).thenReturn(List.of(show));
        assertThat(stage.listAllShows()).contains(show);
    }

    @Test
    void too_expensive_shows_are_filtered_out() {
        Show anyShow = new Show(ANY_UUID, ANY_TITLE, ANY_TIME, ANY_PRICE);
        Show cheapShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, 2);
        Show expensiveShow = new Show(randomUUID(), ANY_TITLE, ANY_TIME, 200);
        when(repository.receive()).thenReturn(List.of(anyShow, cheapShow, expensiveShow));
        assertThat(stage.listShowsBelow(50)).containsOnly(anyShow, cheapShow);
    }
}
