package containers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConcurrentSkipListMapDemo {

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class Event {
        private final ZonedDateTime eventTime;
        private final String content;
    }

    public static void main(String... args) throws InterruptedException {
        ConcurrentNavigableMap<ZonedDateTime, Event> events =
                new ConcurrentSkipListMap<>(Comparator.comparingLong(zdt -> zdt.toInstant().toEpochMilli()));

        for (int i = 0; i < 10; i++) {
            Event event = new Event(ZonedDateTime.now(), String.valueOf(i));
            events.put(event.getEventTime(), event);

            System.out.println(i);
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }

        System.out.println("---");
        ConcurrentNavigableMap<ZonedDateTime, Event> lastEvents = events.tailMap(ZonedDateTime.now().minusSeconds(5));

        Event event = new Event(ZonedDateTime.now(), String.valueOf(666));
        events.put(event.getEventTime(), event);

        lastEvents.forEach((key, value) -> System.out.println(value.getContent()));
    }

}
