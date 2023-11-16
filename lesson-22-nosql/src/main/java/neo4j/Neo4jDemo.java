package neo4j;

import org.neo4j.driver.Query;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.ofPath;
import static org.neo4j.driver.Values.parameters;

/*
 * 1. docker pull neo4j
 * 2. docker run -d --name neo4j -p7474:7474 -p7687:7687 --env NEO4J_AUTH=neo4j/password neo4j:latest
 */
public class Neo4jDemo {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 7687;

    public static void main(String... args) throws Exception {
        try (Neo4jClient client = Neo4jClient.create(HOST_NAME, PORT)) {
            createRelationShip(client);

            // retrieve data
            List<String> companyNames = client.read(session -> {
                Result result = session.run(new Query(
                        "MATCH (company:Company)-[:owns]-> (car:Car)"
                                + " WHERE car.make='tesla' and car.model='modelX'"
                                + " RETURN company.name"));
                List<String> companies = new ArrayList<>();

                while (result.hasNext()) {
                    Record record = result.next();
                    companies.add(record.get(0).asString());
                }

                return companies;
            });

            System.out.format("Company names: %s\n", companyNames);

            // update node
            client.executeUpdateQuery(new Query(
                    "MATCH (car:Car)"
                            + " WHERE car.make='tesla'"
                            + " SET car.milage=$milage"
                            + " SET car :Car:Electro"
                            + " SET car.model=NULL"
                            + " RETURN car",
                    parameters("milage", 120)));
        }
    }

    private static void createRelationShip(Neo4jClient client) {
        // create relationship
        client.executeUpdateQuery(new Query(
                "CREATE (baeldung:Company {name:$company})"
                        + " -[:owns]-> (tesla:Car {make: 'tesla', model: $model})"
                        + " RETURN baeldung, tesla",
                parameters("company", "Baeldung",
                           "model", "modelX")));
    }

}
