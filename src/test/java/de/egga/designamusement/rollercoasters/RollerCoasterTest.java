package de.egga.designamusement.rollercoasters;

import org.junit.jupiter.api.Test;

import static de.egga.designamusement.rollercoasters.RollerCoasterTypes.COUNT_VON_ROUND;
import static org.assertj.core.api.Assertions.assertThat;

public class RollerCoasterTest {

    @Test
    void type_is_available() {
        RollerCoaster rollerCoaster = new RollerCoaster(COUNT_VON_ROUND);
        assertThat(rollerCoaster.getType()).isEqualTo(COUNT_VON_ROUND);
    }
}
