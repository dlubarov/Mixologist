package com.lubarov.daniel.mixologist.events;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Manages listeners for a particular event type.
 *
 * @param <T> the event type
 */
public class EventManager<T> {
    private final Map<EventListener<T>, Void> listeners = new WeakHashMap<>();

    public synchronized void addListener(EventListener<T> listener) {
        listeners.put(listener, null);
    }

    public synchronized void notifyAll(T event) {
        for (EventListener<T> listener : listeners.keySet())
            listener.consume(event);
    }
}
