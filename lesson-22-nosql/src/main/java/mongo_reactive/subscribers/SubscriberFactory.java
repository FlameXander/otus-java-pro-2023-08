package mongo_reactive.subscribers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SubscriberFactory {

    public static <T> SetSubscriber<T> setSubscriber(String id) {
        return new SetSubscriber<>(id);
    }

    public static <T> SingleSubscriber<T> singleSubscriber(String id) {
        return new SingleSubscriber<>(id);
    }

}
