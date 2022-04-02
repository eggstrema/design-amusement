package de.egga.designamusement.rollercoasters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static de.egga.designamusement.rollercoasters.RollerCoasterTest.ANY_ROLLER_COASTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollerCoasterServiceTest {

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
