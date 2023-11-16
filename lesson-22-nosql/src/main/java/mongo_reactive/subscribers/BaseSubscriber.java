package mongo_reactive.subscribers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class BaseSubscriber<T> implements Subscriber<T> {

    protected final String id;
    protected final Set<T> items = new HashSet<>();
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        // Тут можно запрашивать любое кол-во
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        items.add(item);
        // Тут можно запрашивать любое кол-во
        subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        System.out.format(">> %s: onError(%s)\n", id, e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.format(">> %s: onComplete()\n", id);
    }

}
