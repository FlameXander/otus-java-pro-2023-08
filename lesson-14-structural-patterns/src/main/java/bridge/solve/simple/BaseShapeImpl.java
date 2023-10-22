package bridge.solve.simple;

import bridge.solve.Shape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

// Это "левая" часть моста - вершина дерева имплементаций
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseShapeImpl implements Shape {

    private final String shapeId;

    @Override
    public void draw() {
        System.out.format("Drawing %s\n", shapeId);
    }
}
