package neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import jnr.ffi.annotations.SaveError;
import lombok.Getter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@Getter
@SaveError
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Movie {

    @Id
    @GeneratedValue
    Long id;
    private String title;
    private int released;
    private String tagline;

    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private List<Role> roles;

}
