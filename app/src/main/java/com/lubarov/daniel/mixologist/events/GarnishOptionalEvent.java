package com.lubarov.daniel.mixologist.events;

public class GarnishOptionalEvent {
    public static final EventManager<GarnishOptionalEvent> MANAGER = new EventManager<>();

    private final boolean garnishOptional;

    public GarnishOptionalEvent(boolean garnishOptional) {
        this.garnishOptional = garnishOptional;
    }

    public boolean isGarnishOptional() {
        return garnishOptional;
    }
}
