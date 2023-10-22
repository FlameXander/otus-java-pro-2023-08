package decorator.basic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicDemo {

    public static void main(String... args) {
        // Исходный объект
        Component component = new ConcreteComponent();

        /*
          Декораторы добавляют дополнительные свойства исходному объекту динамически.
          Можем формировать цепочку декораторов в run-time
         */
        component = new ConcreteDecoratorA(component);
        component = new ConcreteDecoratorB(component);
        component.operation();
    }

}
