package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

//    protected List<IMapElement> elements = new ArrayList<>();
    Map<Vector2d, IMapElement> elements = new HashMap<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    protected boolean isFreeAnimal(Vector2d position) {
        IMapElement tmp = elements.get(position);
        if (tmp == null)
            return true;
        return !(tmp.getClass() == Animal.class);
    }

    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        elements.put(newPosition, elements.remove(oldPosition));
    }
}
