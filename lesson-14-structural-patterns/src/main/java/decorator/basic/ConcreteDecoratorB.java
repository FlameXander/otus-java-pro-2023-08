package decorator.basic;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteDecoratorB implements Component {

    private final Component delegate;

    @Override
    public void operation() {
        System.out.println("DecoratorB:operation()");
        delegate.operation();
    }

}
