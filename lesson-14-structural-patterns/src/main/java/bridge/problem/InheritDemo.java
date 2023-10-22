package bridge.problem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InheritDemo {

    public static void main(String... args) {
        /*
         Есть абстракция Shape и несколько конкретных её имплементаций:
         Rectangle implements Shape
         Triangle implements Shape
         */
        new Rectangle().draw();
        new Triangle().draw();

        System.out.println();

        /*
         Мы хотим добавить дополнительное свойство Color к абстракции
         и тем самым должны переписать всё существующее дерево конкретных
         имплементаций
         */
        new Rectangle("black").draw();
        new Rectangle("green").draw();
        new Rectangle("red").draw();

        System.out.println();

        new Triangle("black").draw();
        new Triangle("green").draw();
        new Triangle("red").draw();

        /*
         Проблема. Мы хотим независимо менять абстракции и имплементации,
         конечто, сохраняя контракты.
         */
    }

}
