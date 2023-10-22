package decorator.inherit;

public class ConcreteComponentB extends ConcreteComponentA {

    @Override
    public void operation() {
        System.out.println("ComponentB:operation()");
        super.operation();
    }

}
