package de.egga.designamusement.rollercoasters;

public class RollerCoaster {

    private final RollerCoasterTypes type;

    public RollerCoaster(RollerCoasterTypes type) {
        this.type = type;
    }

    public RollerCoasterTypes getType() {
        return type;
    }
}
