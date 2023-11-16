package neo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@Getter
@Setter
@NodeEntity
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String make;
    @Relationship(direction = INCOMING)
    private Company company;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

}
