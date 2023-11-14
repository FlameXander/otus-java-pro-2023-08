package cassandra;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.net.InetSocketAddress;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CassandraClient implements AutoCloseable {

    private final CqlSession session;
    private String currentKeyspaceName;

    public static CassandraClient create(String hostname, int port, String dataCenter) {
        CqlSessionBuilder builder = CqlSession.builder();
        builder.addContactPoint(new InetSocketAddress(hostname, port));
        builder.withLocalDatacenter(dataCenter);

        CqlSession session = builder.build();
        return new CassandraClient(session);
    }

    /**
     * Method used to create any keyspace - schema.
     *
     * @param keyspaceName     the name of the keyspaceName.
     * @param numberOfReplicas the number of replicas.
     */
    public void createKeyspace(String keyspaceName, int numberOfReplicas) {
        CreateKeyspace createKeyspace = SchemaBuilder.createKeyspace(keyspaceName)
                                                     .ifNotExists()
                                                     .withSimpleStrategy(numberOfReplicas);

        session.execute(createKeyspace.build());
    }

    public void useKeyspace(String keyspaceName) {
        session.execute("USE " + CqlIdentifier.fromCql(keyspaceName));
        currentKeyspaceName = keyspaceName;
    }

    /**
     * Method used to delete the specified schema.
     * It results in the immediate, irreversable removal of the keyspace, including all tables and data contained in the
     * keyspace.
     */
    public void deleteKeyspace() {
        String query = "DROP KEYSPACE " + currentKeyspaceName;
        session.execute(query);
        currentKeyspaceName = null;
    }

    public ResultSet execute(String query) {
        return session.execute(query);
    }

    // ---------- AutoCloseable ----------

    @Override
    public void close() {
        session.close();
    }
}
