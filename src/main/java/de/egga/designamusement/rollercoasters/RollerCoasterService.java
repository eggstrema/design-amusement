package de.egga.designamusement.rollercoasters;

import java.time.Duration;

public class RollerCoasterService {

    private final RollerCoasterRealTimeDataProvider dataProvider;

    public RollerCoasterService(RollerCoasterRealTimeDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Duration getWaitingTime(RollerCoaster rollerCoaster) {
        return dataProvider.getWaitingTime(rollerCoaster.getType());
    }
}
