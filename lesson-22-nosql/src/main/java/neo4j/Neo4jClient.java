package neo4j;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Query;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import redis.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.neo4j.driver.Values.parameters;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Neo4jClient implements AutoCloseable {

    private static final String USERNAME = "neo4j";
    private static final String PASSWORD = "password";

    private final Driver driver;

    public static Neo4jClient create(String hostname, int port) {
        String uri = String.format("bolt://%s:%d", hostname, port);
        AuthToken authToken = AuthTokens.basic(USERNAME, PASSWORD);
        Driver driver = GraphDatabase.driver(uri, authToken);
        return new Neo4jClient(driver);
    }

    public void executeUpdateQuery(Query query) {
        execute(session -> session.run(query));
    }

    public <R> R read(Function<Session, R> task) {
        try (Session session = driver.session()) {
            return task.apply(session);
        }
    }

    public void printGreeting(String message) {
        execute(session -> {
            String greeting = session.writeTransaction(tx -> {
                Query query = new Query(
                        "CREATE (a:Greeting) SET a.message = $message RETURN a.message + ', from node ' + id(a)",
                        parameters("message", message));
                Result result = tx.run(query);
                return result.single().get(0).asString();
            });

            System.out.println(greeting);
        });
    }

    public void execute(Consumer<Session> task) {
        try (Session session = driver.session()) {
            task.accept(session);
        }
    }

    public void executeUpdate1(Consumer<Session> task) {
        try (Session session = driver.session()) {
            task.accept(session);
        }
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

}
