package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoClient implements AutoCloseable {

    private final com.mongodb.client.MongoClient client;
    private MongoDatabase currentDatabase;

    public static MongoClient create(String hostname, int port) {
        String connectionString = String.format("mongodb://%s:%d", hostname, port);
        return new MongoClient(MongoClients.create(connectionString));
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

    public Set<String> getDatabaseNames() {
        log("getDatabaseNames()");
        return client.listDatabaseNames().into(new HashSet<>());
    }

    public Set<String> getCollectionNames() {
        log("getCollectionNames()");
        return currentDatabase.listCollectionNames().into(new HashSet<>());
    }

    public void createCollection(String collectionName) {
        if (!getCollectionNames().contains(collectionName))
            currentDatabase.createCollection(collectionName);
    }

    public void deleteCollection(String collectionName) {
        if (getCollectionNames().contains(collectionName)) {
            currentDatabase.getCollection(collectionName).drop();
        }
    }

    public InsertOneResult insertOne(Document document, String collectionName) {
        MongoCollection<Document> collection = currentDatabase.getCollection(collectionName);
        return collection.insertOne(document);
    }

    public UpdateResult updateOne(String id, Bson update, String collectionName) {
        MongoCollection<Document> collection = currentDatabase.getCollection(collectionName);
        return collection.updateOne(new BasicDBObject("_id", new ObjectId(id)),
                                    new BasicDBObject("$set", update));
    }

    public Iterator<Document> find(String collectionName) {
        return currentDatabase.getCollection(collectionName).find().iterator();
    }

    public Iterator<Document> find(Bson filter, String collectionName) {
        return currentDatabase.getCollection(collectionName).find(filter).iterator();
    }

    public DeleteResult delete(Bson filter, String collectionName) {
        return currentDatabase.getCollection(collectionName).deleteMany(filter);
    }

    private static void log(String format, Object... args) {
        System.out.format("--- [%s] " + format + '\n', MongoClient.class.getSimpleName(), args);
    }

    // ---------- AutoCloseable ----------

    @Override
    public void close() {
        client.close();
    }
}
