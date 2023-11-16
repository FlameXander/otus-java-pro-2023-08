package mongo_reactive.subscribers.adapters;

import com.mongodb.Function;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.reactivestreams.Publisher;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdapterFactory {

    public static Publisher<String> stringPublisher(Publisher<InsertOneResult> publisher) {
        return new InsertOneResultToStringPublisher(publisher);
    }

    public static <T> FindPublisher<T> findPublisher(FindPublisher<Document> publisher,
                                                     Function<Document, T> createItem) {
        return new FindPublisherDocumentAdapter<>(publisher, createItem);
    }

    public static Publisher<Boolean> booleanPublisher(Publisher<UpdateResult> publisher, long matchedCount) {
        return new UpdateResultToBooleanPublisher(publisher, matchedCount);
    }

    public static Publisher<Long> longPublisher(Publisher<DeleteResult> publisher) {
        return new DeleteResultToLongPublisher(publisher);
    }
}
