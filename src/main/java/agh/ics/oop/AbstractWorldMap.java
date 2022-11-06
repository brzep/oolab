package agh.ics.oop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {

    protected List<IMapElement> elements = new ArrayList<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    protected boolean isFreeAnimal(Vector2d position) {
        Iterator<IMapElement> it = elements.iterator();
        IMapElement tmp;
        while (it.hasNext()) {
            tmp = it.next();
            if (tmp.getPosition().equals(position) && tmp.getClass() == Animal.class)
                return false;
        }
        return true;
    }

    public Object objectAt(Vector2d position) {
        Iterator<IMapElement> it = elements.iterator();
        while (it.hasNext()) {
            IMapElement tmp = it.next();
            if (tmp.getPosition().equals((position))) {
                return tmp;
            }
        }
        return null;
    }
}
