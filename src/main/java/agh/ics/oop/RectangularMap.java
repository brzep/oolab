package agh.ics.oop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);            //ograniczenia nie były dokładniej opisane, więc ustalam je w taki sposób
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) && isInBounds(position));
    }

    public boolean place(Animal animal) {
        if (!this.isOccupied(animal.position) && isInBounds(animal.position)) {
            elements.put(animal.position, animal);
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied(Vector2d position) {
        if (!isInBounds(position))
            return true;
        return !isFreeAnimal(position);
    }

    private boolean isInBounds(Vector2d position) {
        return (position.precedes(upperRight) && position.follows(lowerLeft));
    }

}
