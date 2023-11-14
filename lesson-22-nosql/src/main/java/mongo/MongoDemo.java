package mongo;

import mongo.domain.Book;
import mongo.repository.BookRepository;

/*
 * 1. docker pull mongo
 * 2. docker run -p 27017:27017 --name mongo -d mongo:latest
 */
public class MongoDemo {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 27017;

    private static final String DATABASE_NAME = "library";

    public static void main(String... args) {
        try (MongoClient client = MongoClient.create(HOST_NAME, PORT)) {
            client.createDatabase(DATABASE_NAME);
            client.useDatabase(DATABASE_NAME);

            foo(client);

            client.deleteDatabase();
        }
    }

    private static void foo(MongoClient client) {
        System.out.println("Existed databases:");
        client.getDatabaseNames().forEach(System.out::println);

        System.out.format("\nExisted collections in database '%s':\n", client.getCurrentDatabasedName());
        client.getCollectionNames().forEach(System.out::println);

        BookRepository bookRepository = new BookRepository(client);
        bookRepository.createCollection();

        Book book = Book.builder()
                        .title("Effective Java")
                        .author("Joshua Bloch")
                        .subject("Programming")
                        .build();

        String id = bookRepository.insert(book);
        System.out.format("\nInsert book id = '%s'\n", id);

        bookRepository.insert(Book.builder()
                                  .title("Design Patterns")
                                  .author("Erich Gamma")
                                  .subject("Programming")
                                  .build());

        System.out.format("\nBooks in collection '%s':\n", BookRepository.COLLECTION_NAME);
        bookRepository.findAll().forEach(System.out::println);

        Book updatedBook = book.toBuilder()
                               .id(id)
                               .publisher("O'Reilly")
                               .build();
        bookRepository.update(updatedBook);

        System.out.format("Books in collection '%s':\n", BookRepository.COLLECTION_NAME);
        bookRepository.findAll().forEach(System.out::println);

        System.out.format("\nBooks in collection '%s' with title '%s':\n",
                          BookRepository.COLLECTION_NAME,
                          book.getTitle());
        bookRepository.findAllByTitle(book.getTitle()).forEach(System.out::println);

        long totalRemoved = bookRepository.deleteByTitle("Effective Java");
        System.out.format("\nTotal %d documents were removed", totalRemoved);

        bookRepository.deleteCollection();
    }

}
