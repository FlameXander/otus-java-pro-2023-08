package ru.otus.lesson13.example.observer;

public class Event {
    private final String data;

    private final EventType type;

    public Event(final String data, final EventType type) {
        this.data = data;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public EventType getType() {
        return type;
    }
}
