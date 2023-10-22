package decorator.basic;

public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("Component:operation()");
    }

}
