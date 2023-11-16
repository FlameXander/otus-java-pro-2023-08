package mongo_reactive.subscribers.adapters;

import com.mongodb.client.result.UpdateResult;
import org.reactivestreams.Publisher;

final class UpdateResultToBooleanPublisher extends BaseAdapter<Boolean, UpdateResult> {

    public UpdateResultToBooleanPublisher(Publisher<UpdateResult> publisher, long expectedMatchedCount) {
        super(updateResult -> updateResult.getMatchedCount() == expectedMatchedCount, publisher);
    }

}
