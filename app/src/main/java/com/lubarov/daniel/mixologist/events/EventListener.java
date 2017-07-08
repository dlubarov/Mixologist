package com.lubarov.daniel.mixologist.events;

public interface EventListener<T> {
    void consume(T event);
}
