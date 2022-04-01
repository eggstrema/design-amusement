package de.egga.designamusement.rollercoasters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static de.egga.designamusement.rollercoasters.RollerCoasterTypes.COUNT_VON_ROUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollerCoasterServiceTest {

    static final RollerCoaster ANY_ROLLER_COASTER = new RollerCoaster(COUNT_VON_ROUND);

    @Mock
    RollerCoasterRealTimeDataProvider dataProvider;

    @InjectMocks
    RollerCoasterService service;

    @Test
    void name() {
        when(dataProvider.getWaitingTime(ANY_ROLLER_COASTER.getType())).thenReturn(Duration.ofMinutes(10));
        Duration waitingTime = service.getWaitingTime(ANY_ROLLER_COASTER);
        assertThat(waitingTime).isEqualTo(Duration.ofMinutes(10));
    }
}
