package com.lubarov.daniel.mixologist.events;

import com.lubarov.daniel.mixologist.quantities.Unit;

public class PreferredUnitEvent {
    public static final EventManager<PreferredUnitEvent> MANAGER = new EventManager<>();

    private final Unit preferredUnit;

    public PreferredUnitEvent(Unit preferredUnit) {
        this.preferredUnit = preferredUnit;
    }

    public Unit getPreferredUnit() {
        return preferredUnit;
    }
}
