package ru.otus.lesson13.example.observer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {

    private Map<EventType, Set<EventListener>> listeners = new ConcurrentHashMap<>();
    public void fireEvent(Event event) {
        listeners.computeIfAbsent(event.getType(), key -> new HashSet<>())
                .forEach(listener -> {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onEvent(event);
                        }
                    }).start();
                });
    }

    public void addListener(EventListener listener) {
        listeners.computeIfAbsent(listener.accepts(), key -> new HashSet<>()).add(listener);
    }

}
