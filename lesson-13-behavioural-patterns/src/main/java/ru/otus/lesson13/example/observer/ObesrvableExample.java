package ru.otus.lesson13.example.observer;

public class ObesrvableExample {
    public static void main(String[] args) throws Exception {
        EventListener listener1 = new UpdateListener();
        EventListener listener = new DeleteListener();

        EventManager manager = new EventManager();

        manager.addListener(listener1);
        manager.addListener(listener);

        manager.fireEvent(new Event("delete event", EventType.DELETE));
        manager.fireEvent(new Event("event2", EventType.UPDATE));

        manager.fireEvent(new Event("event3", EventType.LIST));
        manager.addListener(new LIstListener());

        manager.fireEvent(new Event("event4", EventType.LIST));
        System.out.println("------------------");
    }
}
