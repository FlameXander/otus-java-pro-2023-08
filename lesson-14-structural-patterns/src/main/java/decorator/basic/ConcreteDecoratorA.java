package decorator.basic;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteDecoratorA implements Component {

    private final Component delegate;

    @Override
    public void operation() {
        System.out.println("DecoratorA:operation()");
        delegate.operation();
    }

}
