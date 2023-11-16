package mongo_reactive.subscribers.adapters;

import com.mongodb.CursorType;
import com.mongodb.ExplainVerbosity;
import com.mongodb.Function;
import com.mongodb.client.model.Collation;
import com.mongodb.reactivestreams.client.FindPublisher;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

final class FindPublisherDocumentAdapter<T> implements FindPublisher<T>, Subscriber<Document> {

    private final Function<Document, T> createItem;
    private Subscription subscription;
    private Subscriber<? super T> subscriber;

    public FindPublisherDocumentAdapter(FindPublisher<Document> publisher,
                                        Function<Document, T> createItem) {
        this.createItem = createItem;
        publisher.subscribe(this);
    }

    // ---------- FindPublisher ----------

    @Override
    public Publisher<T> first() {
        return null;
    }

    @Override
    public FindPublisher<T> filter(Bson filter) {
        return null;
    }

    @Override
    public FindPublisher<T> limit(int limit) {
        return null;
    }

    @Override
    public FindPublisher<T> skip(int skip) {
        return null;
    }

    @Override
    public FindPublisher<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public FindPublisher<T> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public FindPublisher<T> projection(Bson projection) {
        return null;
    }

    @Override
    public FindPublisher<T> sort(Bson sort) {
        return null;
    }

    @Override
    public FindPublisher<T> noCursorTimeout(boolean noCursorTimeout) {
        return null;
    }

    @Override
    public FindPublisher<T> oplogReplay(boolean oplogReplay) {
        return null;
    }

    @Override
    public FindPublisher<T> partial(boolean partial) {
        return null;
    }

    @Override
    public FindPublisher<T> cursorType(CursorType cursorType) {
        return null;
    }

    @Override
    public FindPublisher<T> collation(Collation collation) {
        return null;
    }

    @Override
    public FindPublisher<T> comment(String comment) {
        return null;
    }

    @Override
    public FindPublisher<T> comment(BsonValue comment) {
        return null;
    }

    @Override
    public FindPublisher<T> hint(Bson hint) {
        return null;
    }

    @Override
    public FindPublisher<T> hintString(String hint) {
        return null;
    }

    @Override
    public FindPublisher<T> let(Bson variables) {
        return null;
    }

    @Override
    public FindPublisher<T> max(Bson max) {
        return null;
    }

    @Override
    public FindPublisher<T> min(Bson min) {
        return null;
    }

    @Override
    public FindPublisher<T> returnKey(boolean returnKey) {
        return null;
    }

    @Override
    public FindPublisher<T> showRecordId(boolean showRecordId) {
        return null;
    }

    @Override
    public FindPublisher<T> batchSize(int batchSize) {
        return null;
    }

    @Override
    public FindPublisher<T> allowDiskUse(Boolean allowDiskUse) {
        return null;
    }

    @Override
    public Publisher<Document> explain() {
        return null;
    }

    @Override
    public Publisher<Document> explain(ExplainVerbosity verbosity) {
        return null;
    }

    @Override
    public <E> Publisher<E> explain(Class<E> explainResultClass) {
        return null;
    }

    @Override
    public <E> Publisher<E> explain(Class<E> explainResultClass, ExplainVerbosity verbosity) {
        return null;
    }

    // ---------- Publisher ----------

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(subscription);
    }

    // ---------- Subscriber ----------

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Document document) {
        T item = createItem.apply(document);
        subscriber.onNext(item);
    }

    @Override
    public void onError(Throwable e) {
        subscriber.onError(e);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

}
