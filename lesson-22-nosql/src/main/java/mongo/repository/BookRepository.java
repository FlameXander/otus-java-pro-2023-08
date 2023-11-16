package mongo.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import mongo.MongoClient;
import mongo.domain.Book;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class BookRepository {

    public static final String COLLECTION_NAME = "books";

    private final MongoClient client;

    public void createCollection() {
        client.createCollection(COLLECTION_NAME);
    }

    public void deleteCollection() {
        client.deleteCollection(COLLECTION_NAME);
    }

    public String insert(Book book) {
        Document document = new Document()
                .append("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("subject", book.getSubject())
                .append("publisher", book.getPublisher());

        BsonValue res = Objects.requireNonNull(client.insertOne(document, COLLECTION_NAME).getInsertedId());
        return ((BsonObjectId) res).getValue().toHexString();
    }

    public boolean update(Book book) {
        BasicDBObject update = new BasicDBObject()
                .append("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("subject", book.getSubject())
                .append("publisher", book.getPublisher());

        UpdateResult res = client.updateOne(book.getId(), update, COLLECTION_NAME);
        return res.getMatchedCount() == 1;
    }

    public List<Book> findAll() {
        return createBooks(client.find(COLLECTION_NAME));
    }

    public List<Book> findAllByTitle(String title) {
        Bson filter = Filters.eq("title", title);
        return createBooks(client.find(filter, COLLECTION_NAME));
    }

    public long deleteAllByTitle(String title) {
        Bson filter = Filters.eq("title", title);
        DeleteResult res = client.delete(filter, COLLECTION_NAME);
        return res.getDeletedCount();
    }

    private static List<Book> createBooks(Iterator<Document> it) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED), false)
                            .map(BookRepository::createBook)
                            .collect(Collectors.toList());
    }

    private static Book createBook(Document document) {
        Object id = document.get("_id");

        return Book.builder()
                   .id(id instanceof ObjectId ? ((ObjectId) id).toHexString() : String.valueOf(id))
                   .title(document.getString("title"))
                   .author(document.getString("author"))
                   .subject(document.getString("subject"))
                   .publisher(document.getString("publisher"))
                   .build();
    }

}
