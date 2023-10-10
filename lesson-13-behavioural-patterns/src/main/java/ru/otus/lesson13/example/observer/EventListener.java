package ru.otus.lesson13.example.observer;

public interface EventListener {
    void onEvent(Event event);

    EventType accepts();
}
