package ru.otus.lesson13.example.observer;

public class UpdateListener implements EventListener{

    @Override
    public void onEvent(Event event) {
        System.out.println("Update " + event.getData());
    }

    @Override
    public EventType accepts() {
        return EventType.UPDATE;
    }
}
