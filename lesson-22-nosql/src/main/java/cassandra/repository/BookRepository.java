package cassandra.repository;

import cassandra.CassandraClient;
import cassandra.domain.Book;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookRepository {

    private static final String TABLE_NAME = "books";
    private static final String TABLE_NAME_BY_TITLE = TABLE_NAME + "ByTitle";

    private final CassandraClient client;

    /** Creates the books table. */
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(id uuid PRIMARY KEY, title text, author text, subject text);";
        client.execute(query);
    }

    /** Creates the books table. */
    public void createTableByTitle() {
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BY_TITLE + "(id uuid, title text, PRIMARY KEY (title, id));";
        client.execute(query);
    }

    /** Alters the table books and adds an extra column. */
    public void alterTable(String columnName, String columnType) {
        String query = String.format("ALTER TABLE %s ADD %s %s;", TABLE_NAME, columnName, columnType);
        client.execute(query);
    }

    /** Insert a row in the table books. */
    public void insert(Book book) {
        String query = String.format("INSERT INTO %s(id, title, author, subject) VALUES (%s, '%s', '%s', '%s');",
                                     TABLE_NAME, book.getId(), book.getTitle(), book.getAuthor(), book.getSubject());
        client.execute(query);
    }

    /** Insert a row in the table booksByTitle. */
    public void insertByTitle(Book book) {
        String query = String.format("INSERT INTO %s(id, title) VALUES (%s, '%s');",
                                     TABLE_NAME_BY_TITLE, book.getId(), book.getTitle());

        client.execute(query);
    }

    /** Insert a book into two identical tables using a batch query. */
    public void insertBatch(Book book) {
        String query = String.format(
                "BEGIN BATCH INSERT INTO %s(id, title, author, subject)"
                        + " VALUES (%s, '%s', '%s', '%s');"
                        + "INSERT INTO %s(id, title) VALUES (%s, '%s');"
                        + "APPLY BATCH;",
                TABLE_NAME, book.getId(), book.getTitle(), book.getAuthor(), book.getSubject(),
                TABLE_NAME_BY_TITLE, book.getId(), book.getTitle());

        client.execute(query);
    }

    /** Select book by id. */
    public Book selectByTitle(String title) {
        String query = String.format("SELECT * FROM %s WHERE title = '%s';", TABLE_NAME_BY_TITLE, title);
        ResultSet rs = client.execute(query);

        List<Book> books = new ArrayList<>();

        for (Row row : rs) {
            Book book = Book.builder()
                            .id(row.getUuid("id"))
                            .title(row.getString("title"))
                            .build();
            books.add(book);
        }

        return books.get(0);
    }

    /** Select all books from books */
    public List<Book> findAll() {
        String query = "SELECT * FROM " + TABLE_NAME;
        ResultSet rs = client.execute(query);

        List<Book> books = new ArrayList<>();

        for (Row row : rs) {
            Book book = Book.builder()
                            .id(row.getUuid("id"))
                            .title(row.getString("title"))
                            .author(row.getString("author"))
                            .subject(row.getString("subject"))
                            .build();
            books.add(book);
        }

        return books;
    }

    /** Select all books from booksByTitle */
    public List<Book> findAllByTitle() {
        String query = "SELECT * FROM " + TABLE_NAME_BY_TITLE;
        ResultSet rs = client.execute(query);

        List<Book> books = new ArrayList<>();

        for (Row row : rs) {
            Book book = Book.builder()
                            .id(row.getUuid("id"))
                            .title(row.getString("title"))
                            .build();
            books.add(book);
        }

        return books;
    }

    /** Delete a book by title. */
    public void deleteByTitle(String title) {
        String query = String.format("DELETE FROM %s WHERE title = '%s';", TABLE_NAME_BY_TITLE, title);
        client.execute(query);
    }

    /** Delete table. */
    public void deleteTable(String tableName) {
        String query = "DROP TABLE IF EXISTS " + tableName;
        client.execute(query);
    }
}
