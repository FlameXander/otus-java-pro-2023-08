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
            client.useDatabase(DATABASE_NAME);
            foo(client);
            client.deleteDatabase();
        }
    }

    private static void foo(MongoClient client) {
        printDatabaseNames(client);

        BookRepository bookRepository = new BookRepository(client);
        bookRepository.createCollection();
        printCollections(client);

        Book book = Book.builder()
                        .title("Effective Java")
                        .author("Joshua Bloch")
                        .subject("Programming")
                        .build();

        String id = insertBook(book, bookRepository);
        insertBook(Book.builder()
                       .title("Design Patterns")
                       .author("Erich Gamma")
                       .subject("Programming")
                       .build(), bookRepository);

        printAllBooks(bookRepository);

        Book updateBook = book.toBuilder()
                              .id(id)
                              .publisher("O'Reilly")
                              .build();
        updateBook(updateBook, bookRepository);

        printAllBooks(bookRepository);
        findAllByTitle(book, bookRepository);
        deleteAllByTitle(book, bookRepository);

        bookRepository.deleteCollection();
    }

    private static void printDatabaseNames(MongoClient client) {
        System.out.println("Existed databases:");
        client.getDatabaseNames().forEach(System.out::println);
    }

    private static void printCollections(MongoClient client) {
        System.out.format("\nExisted collections in database '%s':\n", client.getCurrentDatabasedName());
        client.getCollectionNames().forEach(System.out::println);
    }

    private static String insertBook(Book book, BookRepository bookRepository) {
        String id = bookRepository.insert(book);
        System.out.format("\nInsert book id = '%s'\n", id);
        return id;
    }

    private static void printAllBooks(BookRepository bookRepository) {
        System.out.format("\nBooks in collection '%s':\n", BookRepository.COLLECTION_NAME);
        bookRepository.findAll().forEach(System.out::println);
    }

    private static void updateBook(Book book, BookRepository bookRepository) {
        bookRepository.update(book);
    }

    private static void findAllByTitle(Book book, BookRepository bookRepository) {
        System.out.format("\nBooks in collection '%s' with title '%s':\n",
                          BookRepository.COLLECTION_NAME, book.getTitle());
        bookRepository.findAllByTitle(book.getTitle()).forEach(System.out::println);
    }

    private static void deleteAllByTitle(Book book, BookRepository bookRepository) {
        long totalRemoved = bookRepository.deleteAllByTitle(book.getTitle());
        System.out.format("\nTotal %d documents were removed\n", totalRemoved);

        System.out.format("\nBooks in collection '%s' with title '%s':\n",
                          BookRepository.COLLECTION_NAME, book.getTitle());
        bookRepository.findAllByTitle(book.getTitle()).forEach(System.out::println);
    }

}
