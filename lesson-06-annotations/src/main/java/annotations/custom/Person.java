package annotations.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@CustomAnnotation(age = 22, letters = { "three", "four" })
public final class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
}
