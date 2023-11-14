package cassandra;

import cassandra.domain.Book;
import cassandra.repository.BookRepository;

import java.util.UUID;

/*
 * 1. docker pull cassandra
 * 2. docker run -p 7000:7000 -p 7001:7001 -p 7199:7100 -p 9042:9042 -p 9160:9160 --name cassandra -d cassandra:latest
 */
public class CassandraDemo {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 9042;
    private static final String DATA_CENTER = "datacenter1";

    private static final String KEYSPACE_NAME = "library";
    private static final int NUMBER_OF_REPLICAS = 1;

    public static void main(String... args) {
        try (CassandraClient client = CassandraClient.create(HOST_NAME, PORT, DATA_CENTER)) {
            client.createKeyspace(KEYSPACE_NAME, NUMBER_OF_REPLICAS);
            client.useKeyspace(KEYSPACE_NAME);

            foo(client);

            client.deleteKeyspace();
        }
    }

    private static void foo(CassandraClient client) {
        BookRepository bookRepository = new BookRepository(client);
        bookRepository.createTable();
        bookRepository.alterTable("publisher", "text");

        bookRepository.createTableByTitle();

        Book book = Book.builder()
                        .id(UUID.randomUUID())
                        .title("Effective Java")
                        .author("Joshua Bloch")
                        .subject("Programming")
                        .build();

        bookRepository.insertBatch(book);

        bookRepository.findAll().forEach(b -> System.out.println("Title in books: " + b.getTitle()));
        bookRepository.findAllByTitle()
                      .forEach(b -> System.out.println("Title in booksByTitle: " + b.getTitle()));

        bookRepository.deleteByTitle("Effective Java");
        bookRepository.deleteTable("books");
        bookRepository.deleteTable("booksByTitle");
    }
}

