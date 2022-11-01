package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    ArrayList<ArrayList<Object>> map = new ArrayList<ArrayList<Object>>();

    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);            //ograniczenia nie były dokładniej opisane, więc ustalam je w taki sposób
        this.upperRight = new Vector2d(width - 1, height - 1);
        for (int i = 0; i < width; i++) {
            map.add(new ArrayList<Object>());
            for (int j = 0; j < height; j++) {
                map.get(i).add(null);
            }
        }
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
//        return map.toString();
        return visualizer.draw(lowerLeft, upperRight);
    }

    private boolean isInBounds(Vector2d position) {
        return (position.precedes(upperRight) && position.follows(lowerLeft));
    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) && isInBounds(position));
    }

    public boolean place(Animal animal) {
        if (!this.isOccupied(animal.position) && isInBounds(animal.position)) {
            map.get(animal.position.x).set(animal.position.y, animal);
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied(Vector2d position) {
        if (!isInBounds(position))
                return true;
        return !(map.get(position.x).get(position.y) == null);
    }


    public Object objectAt(Vector2d position) {
        return map.get(position.x).get(position.y);
    }

    public void relocate(Vector2d curr, Vector2d target) {
        map.get(target.x).set(target.y, objectAt(curr));
        map.get(curr.x).set(curr.y, null);
    }

}
