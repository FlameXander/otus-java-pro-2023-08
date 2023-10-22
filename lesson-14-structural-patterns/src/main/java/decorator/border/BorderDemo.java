package decorator.border;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BorderDemo {

    public static void main(String... args) {
        new TextView().draw();  // aTextView
        System.out.println();
        new ScrollDecorator(new TextView()).draw();    // aTextView + Scroll
        System.out.println();
        new BorderDecorator(new TextView()).draw();    // aTextView + Border
        System.out.println();
        new BorderDecorator(new ScrollDecorator(new TextView())).draw();    // aTextView + Scroll + Border
    }
}
