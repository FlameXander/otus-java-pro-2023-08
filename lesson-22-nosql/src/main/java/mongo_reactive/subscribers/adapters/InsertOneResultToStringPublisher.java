package mongo_reactive.subscribers.adapters;

import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.reactivestreams.Publisher;

import java.util.Objects;

final class InsertOneResultToStringPublisher extends BaseAdapter<String, InsertOneResult> {

    public InsertOneResultToStringPublisher(Publisher<InsertOneResult> publisher) {
        super(InsertOneResultToStringPublisher::convert, publisher);
    }

    private static String convert(InsertOneResult insertOneResult) {
        BsonValue res = Objects.requireNonNull(insertOneResult.getInsertedId());
        return ((BsonObjectId) res).getValue().toHexString();
    }

}
