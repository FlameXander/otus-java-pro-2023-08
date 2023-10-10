package ru.otus.lesson13.example.observer;


public class LIstListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        Delayer.delay();
        System.out.println("List fired" + event.getData());
    }

    @Override
    public EventType accepts() {
        return EventType.LIST;
    }
}
