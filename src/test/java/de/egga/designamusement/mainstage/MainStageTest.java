package de.egga.designamusement.mainstage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainStageTest {

    public static final LocalTime ANY_TIME = LocalTime.parse("16:00:00", ISO_TIME);
    public static final String ANY_TITLE = "Julia & Oscar the Grouch";
    @Mock
    MainStageRepository repository;

    @InjectMocks
    MainStage stage;

    @Test
    void new_show_is_contained_in_list_of_all_shows() {
        Show newShow = new Show(ANY_TITLE, ANY_TIME);
        stage.add(newShow);
        assertThat(stage.listAllShows()).contains(newShow);
    }

    @Test
    void new_show_is_persisted_in_repository() {
        Show newShow = new Show(ANY_TITLE, ANY_TIME);
        stage.add(newShow);
        verify(repository).save(newShow);
    }

    @Test
    void list_all_shows_fetches_updates_from_repository() {
        Show show = new Show(ANY_TITLE, ANY_TIME);
        when(repository.receive()).thenReturn(List.of(show));

        assertThat(stage.listAllShows()).contains(show);
    }
}
