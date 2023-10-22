package bridge.solve.colored;

import bridge.solve.color.Color;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

// Это "левая" часть моста - вершина дерева имплементаций реализующая цвет
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseColorShapeImpl implements ColorShape {

    private final String shapeId;
    /*
      Тут также есть другой мост (ссылка) на объект Color.
      Снова, про конкретную имплементацию ничего не знаем?
     */
    private final Color color;

    @Override
    public void draw() {
        System.out.println("Drawing " + shapeId + ". " + color.fill());
    }
}
