package decorator.border;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScrollDecorator implements Component {

    private final Component component;

    @Override
    public void draw() {
        component.draw();
        System.out.print(" + Scroll");
    }

}
