package lab6;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Action> list;
    private int position;

    public History() {
        list = new ArrayList<>();
        position = -1;
    }

    public void clear() {
        list.clear();
        position = 0;
    }

    public void addShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("shape cannot be null");
        }
        incrementPosition();
        list.add(new DrawAction(shape));
    }

    public List<Action> removeShape(double x, double y) {
        Shape shapeToRemove = null;
        for (int i = list.size() - 1; i >= 0; --i) {
            var item = list.get(i);
            if (item instanceof DrawAction && item.getShape().getBounds().contains(x, y) && !isRemoved(i)) {
                shapeToRemove = list.get(i).getShape();
                break;
            }
        }
        List<Action> result = new ArrayList<>();
        if (shapeToRemove == null) {
            return result;
        }
        incrementPosition();
        var clearAction = new ClearAction(shapeToRemove);
        list.add(clearAction);
        result.add(clearAction);
        for (int i = 0; i < position; ++i) {
            var item = list.get(i);
            if (item instanceof DrawAction && item.getShape().getBounds().intersects(shapeToRemove.getBounds())
                    && !isRemoved(i)) {
                result.add(item);
            }
        }
        return result;
    }

    private boolean isRemoved(int itemIndex) {
        for (int i = itemIndex + 1; i <= position; ++i) {
            var item = list.get(i);
            if (item instanceof ClearAction && item.shape == list.get(itemIndex).getShape()) {
                return true;
            }
        }
        return false;
    }

    private void incrementPosition() {
        ++position;
        while (position != list.size()) {
            list.remove(position);
        }
    }

    public List<Action> undo() {
        List<Action> result = new ArrayList<>();
        if (position < 0) {
            return result;
        }
        var lastItem = list.get(position);
        --position;
        if (lastItem instanceof ClearAction) {
            result.add(new DrawAction(lastItem.getShape()));
            return result;
        }
        var bounds = lastItem.getShape().getBounds();
        result.add(new ClearAction(lastItem.getShape()));
        for (int i = 0; i <= position; ++i) {
            var item = list.get(i);
            if (item instanceof DrawAction && item.getShape().getBounds().intersects(bounds) && !isRemoved(i)) {
                result.add(item);
            }
        }
        return result;
    }
}
