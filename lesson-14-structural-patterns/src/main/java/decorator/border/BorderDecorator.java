package decorator.border;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BorderDecorator implements Component {

    private final Component component;

    @Override
    public void draw() {
        component.draw();
        System.out.print(" + Border");
    }

}
