package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Math.max;

public class MapBoundary implements IPositionChangeObserver {

    private final Vector2d minBound;
    private final static class setElement {
        private final Vector2d position;
        private final Class<?> type;

        public setElement(Vector2d p, Class<?> t) {
            position = p;
            type = t;
        }
        public setElement(IMapElement element) {
            position = element.getPosition();
            type = element.getClass();
        }
        public Vector2d getPosition() {
            return position;
        }
        public Class<?> getType() {
            return type;
        }
    }
    SortedSet<setElement> xPosition = new TreeSet<>(new Comparator<>() {
        @Override
        public int compare(setElement a, setElement b) {
            int res = a.getPosition().compareX(b.getPosition());
            if (res == 0)
                if (a.getType() == Animal.class)
                    if (b.getType() == Animal.class)
                        return 0;
                    else
                        return 1;
                else if (b.getType() == Animal.class)
                    return -1;
                else
                    return 0;
            else
                return res;
        }
    });

    SortedSet<setElement> yPosition = new TreeSet<>(new Comparator<>() {
        @Override
        public int compare(setElement a, setElement b) {
            int res = a.getPosition().compareY(b.getPosition());
            if (res == 0)
                if (a.getType() == Animal.class)
                    if (b.getType() == Animal.class)
                        return 0;
                    else
                        return 1;
                else if (b.getType() == Animal.class)
                    return -1;
                else
                    return 0;
            else
                return res;
        }
    });

    public MapBoundary(Vector2d minBd) {
        minBound = minBd;
    }
    public void add(IMapElement subject) {
        setElement tmp = new setElement(subject);
        xPosition.add(tmp);
        yPosition.add(tmp);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        setElement element = new setElement(oldPosition, Animal.class);
        if (xPosition.contains(element)) {
            xPosition.remove(element);
            yPosition.remove(element);
            element = new setElement(newPosition, Animal.class);
            xPosition.add(element);
            yPosition.add(element);
        }
        element = new setElement(oldPosition, Grass.class);
        if (xPosition.contains(element)) {
            xPosition.remove(element);
            yPosition.remove(element);
            element = new setElement(newPosition, Animal.class);
            xPosition.add(element);
            yPosition.add(element);
        }
    }

    public Vector2d getBound() {
        return new Vector2d(max(xPosition.last().getPosition().x, minBound.x), max(yPosition.last().getPosition().y, minBound.y));
    }

    public void remove(Vector2d position, Class<?> type) {
        xPosition.remove(new setElement(position, type));
        yPosition.remove(new setElement(position, type));
    }

}
