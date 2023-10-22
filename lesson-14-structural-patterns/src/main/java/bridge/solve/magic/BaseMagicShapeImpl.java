package bridge.solve.magic;

import bridge.solve.colored.ColorShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

// Это "левая" часть моста - вершина дерева имплементаций реализующая магические свойства
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseMagicShapeImpl implements ColorShape {

    private final String shapeId;
    private final String description;

    @Override
    public void draw() {
        System.out.println("Drawing " + shapeId + ". " + description);
    }
}
