package mongo_reactive.subscribers;

import java.util.Collections;
import java.util.Set;

public final class SetSubscriber<T> extends BaseSubscriber<T> {

    private boolean completed;

    SetSubscriber(String id) {
        super(id);
    }

    public Set<T> getItems() {
        while (!completed) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return Collections.unmodifiableSet(items);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        completed = true;
    }
}

