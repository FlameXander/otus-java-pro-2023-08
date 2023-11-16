package neo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@NodeEntity
public class Company {

    private Long id;
    private String name;
    @Relationship(type = "owns")
    private Car car;

    public Company(String name) {
        this.name = name;
    }

}
