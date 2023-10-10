package ru.otus.lesson13.example.observer;


public class DeleteListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        Delayer.delay();
        System.out.println("Delete" + event.getData());
    }

    @Override
    public EventType accepts() {
        return EventType.DELETE;
    }
}
