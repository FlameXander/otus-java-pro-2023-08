package mongo_reactive.subscribers;

import java.util.concurrent.CountDownLatch;

public final class SingleSubscriber<T> extends BaseSubscriber<T> {

    private final CountDownLatch countDownLatch = new CountDownLatch(2);

    SingleSubscriber(String id) {
        super(id);
    }

    public T getItem() {
        try {
            countDownLatch.countDown();
            countDownLatch.await();
            return items.iterator().next();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        countDownLatch.countDown();
    }
}
