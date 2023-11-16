package mongo_reactive.subscribers;

import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonObjectId;
import org.bson.BsonValue;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public final class StringToInsertOneResultSubscriberAdapter extends BaseSubscriber<InsertOneResult> {

    private final CountDownLatch countDownLatch;

    StringToInsertOneResultSubscriberAdapter(String id, CountDownLatch countDownLatch) {
        super(id);
        this.countDownLatch = countDownLatch;
    }

    public String getItem() {
        BsonValue res = Objects.requireNonNull(items.iterator().next().getInsertedId());
        return ((BsonObjectId) res).getValue().toHexString();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        countDownLatch.countDown();
    }
}
