package com.lubarov.daniel.mixologist.events;

public interface EventListener<T> {
    public void consume(T event);
}
