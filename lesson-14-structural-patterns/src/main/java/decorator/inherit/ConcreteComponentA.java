package decorator.inherit;

public class ConcreteComponentA extends ConcreteComponent {

    @Override
    public void operation() {
        System.out.println("ComponentA:operation()");
        super.operation();
    }

}
