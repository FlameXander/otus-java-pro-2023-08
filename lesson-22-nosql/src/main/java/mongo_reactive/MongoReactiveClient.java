package mongo_reactive;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import mongo_reactive.subscribers.SetSubscriber;
import mongo_reactive.subscribers.SubscriberFactory;
import mongo_reactive.subscribers.adapters.AdapterFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoReactiveClient implements AutoCloseable {

    private final MongoClient client;
    private MongoDatabase currentDatabase;

    public static MongoReactiveClient create(String hostname, int port) {
        String connectionString = String.format("mongodb://%s:%d", hostname, port);
        return new MongoReactiveClient(MongoClients.create(connectionString));
    }

    public String getCurrentDatabasedName() {
        return currentDatabase == null ? null : currentDatabase.getName();
    }

    public void useDatabase(String databaseName) {
        log("useDatabase(%s)", databaseName);
        currentDatabase = client.getDatabase(databaseName);
    }

    public void deleteDatabase() {
        if (currentDatabase != null)
            currentDatabase.drop();

        currentDatabase = null;
    }

    public Publisher<String> getDatabaseNames() {
        log("getDatabaseNames()");
        return client.listDatabaseNames();
    }

    public Publisher<String> getCollectionNames() {
        log("getCollectionNames()");
        return currentDatabase.listCollectionNames();
    }

    public void createCollection(String collectionName) {
        SetSubscriber<String> subscriber = SubscriberFactory.setSubscriber("CreateCollection");
        getCollectionNames().subscribe(subscriber);

        if (!subscriber.getItems().contains(collectionName))
            currentDatabase.createCollection(collectionName);
    }

    public void deleteCollection(String collectionName) {
        SetSubscriber<String> subscriber = SubscriberFactory.setSubscriber("DeleteCollection");
        getCollectionNames().subscribe(subscriber);

        if (subscriber.getItems().contains(collectionName))
            currentDatabase.getCollection(collectionName).drop();
    }

    public Publisher<String> insertOne(Document document, String collectionName) {
        MongoCollection<Document> collection = currentDatabase.getCollection(collectionName);
        return AdapterFactory.stringPublisher(collection.insertOne(document));
    }

    public Publisher<UpdateResult> updateOne(String id, Bson update, String collectionName) {
        MongoCollection<Document> collection = currentDatabase.getCollection(collectionName);
        return collection.updateOne(new BasicDBObject("_id", new ObjectId(id)),
                                    new BasicDBObject("$set", update));
    }

    public FindPublisher<Document> find(String collectionName) {
        return currentDatabase.getCollection(collectionName).find();
    }

    public FindPublisher<Document> find(Bson filter, String collectionName) {
        return currentDatabase.getCollection(collectionName).find(filter);
    }

    public Publisher<DeleteResult> delete(Bson filter, String collectionName) {
        return currentDatabase.getCollection(collectionName).deleteMany(filter);
    }

    private static void log(String format, Object... args) {
        System.out.format("--- [%s] " + format + '\n', mongo.MongoClient.class.getSimpleName(), args);
    }

    // ---------- AutoCloseable ----------

    @Override
    public void close() throws Exception {
        client.close();
    }
}
