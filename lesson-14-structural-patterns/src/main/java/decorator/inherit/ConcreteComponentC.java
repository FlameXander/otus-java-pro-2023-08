package decorator.inherit;

public class ConcreteComponentC extends ConcreteComponent {

    @Override
    public void operation() {
        System.out.println("ComponentC:operation()");
        super.operation();
    }

}
