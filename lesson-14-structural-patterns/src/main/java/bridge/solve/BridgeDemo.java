package bridge.solve;

import bridge.solve.color.BlackColor;
import bridge.solve.color.GreenColor;
import bridge.solve.color.RedColor;
import bridge.solve.colored.ColorRectangle;
import bridge.solve.colored.ColorTriangle;
import bridge.solve.magic.LevitationAbility;
import bridge.solve.simple.Rectangle;
import bridge.solve.simple.Triangle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/*
 Наш мост "Shape <-> BaseShapeImpl". Каждая сущность представляет независимое,
 но согласованное дерево. Каждая из этих часть может быть изменена (при условии
 соблюдения контракта) независимо друг от друга.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BridgeDemo {

    public static void main(String... args) {
        /*
         Есть абстракция Shape и несколько конкретных её имплементаций:
         Rectangle implements Shape
         Triangle implements Shape

         Во время выполнения программы мы точно знаем конкретную имплементацию
         */
        draw(new Rectangle());
        draw(new Triangle());

        System.out.println();

        /*
         Всё работает, но мы решили добавить изменить абстракцию, добавив цвет,
         при этому не сломав уже существующий имплементации.
         Для этого добавим новую абстракцию ColorShape extends Shape у которой
         свойство Color представлено отдельной иерархией (мы конкретно изменили
         дерево абстракций, при этому существующее дерево имплементаций не изменилось)
         */
        draw(new Rectangle()); // не изменились
        draw(new Triangle());  // не изменились

        System.out.println();

        /*
         Теперь мы хотим изменить имплементации, добавить возможность цвета.
         Поэтому вносим необходимые правки в дерево имплементаций:
         ColorTriangle extends Triangle implements ColorShape
         */
        draw(new ColorRectangle(new RedColor()));
        draw(new ColorRectangle(new GreenColor()));
        draw(new ColorRectangle(new BlackColor()));

        draw(new ColorTriangle(new RedColor()));
        draw(new ColorTriangle(new GreenColor()));
        draw(new ColorTriangle(new BlackColor()));

        System.out.println();

        /*
         У нас есть независимое дерево абстракций Shape и независимые деревья
         имплементаций BaseShapeImpl и BaseColorShapeImpl.

         Но нам ничего не стоить придумать новую имплементацию, использую
         существующее дерево абстракций. Т.е. придумать ещё одну "правую" часть
         моста. При этом дерево абстракций ("левую" часть моста изменять не нужно).

         BaseMagicShapeImpl
         */

        draw(new LevitationAbility());
    }

    private static void draw(Shape shape) {
        /*
          Тут мы ничего не знаем про конкретную имплементацию,
          работаем только с абстракцией Shape
         */
        shape.draw();
    }

}
