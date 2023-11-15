package mongo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Book {

    private final String id;
    private final String title;
    private final String author;
    private final String subject;
    private final String publisher;

}
