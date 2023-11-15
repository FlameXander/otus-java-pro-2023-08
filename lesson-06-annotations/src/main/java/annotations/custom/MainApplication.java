package annotations.custom;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MainApplication {
    public static void main(String... args) throws IllegalAccessException, ClassNotFoundException {
        Person person = new Person("Oleg", "Cherednik", 43);

        Class<Person> cls = Person.class;
        Class<Person> cls1 = (Class<Person>) person.getClass();
        Class<Person> cls2 = (Class<Person>) MainApplication.class.getClassLoader().loadClass(
                "annotations.custom.Person");
        System.out.println(cls.getName());
        System.out.println(cls.getSimpleName());

        System.out.println();

        CustomAnnotation ann = cls.getAnnotation(CustomAnnotation.class);
        System.out.println("age: " + ann.age());
        System.out.println("letters: " + Arrays.toString(ann.letters()));

        System.out.println();

        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + " = " + field.get(person));
            field.setAccessible(false);
        }

//        System.out.println(person.getFirstName());
//        System.out.println(person.getLastName());
//        System.out.println(person.getAge());

        System.out.println();

        System.out.println(person);
    }

}
