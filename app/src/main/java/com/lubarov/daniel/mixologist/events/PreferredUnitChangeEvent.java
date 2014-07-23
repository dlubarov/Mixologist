package com.lubarov.daniel.mixologist.events;

import com.lubarov.daniel.mixologist.quantities.Unit;

public class PreferredUnitChangeEvent {
    public static final EventManager<PreferredUnitChangeEvent> MANAGER = new EventManager<>();

    private final Unit preferredUnit;

    public PreferredUnitChangeEvent(Unit preferredUnit) {
        this.preferredUnit = preferredUnit;
    }

    public Unit getPreferredUnit() {
        return preferredUnit;
    }
}
