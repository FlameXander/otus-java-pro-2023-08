package decorator.inherit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InheritDemo {

    public static void main(String... args) {
        /*
         При использовании наследования, нет возможности динамически добавлять
         нужные свойства объекту. Только путём нового наследования на этапе компиляции.
         */
        new ConcreteComponentB().operation();
        System.out.println();
        new ConcreteComponentC().operation();
    }

}
