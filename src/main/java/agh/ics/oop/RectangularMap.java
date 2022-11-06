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
            elements.add(animal);
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

    public void relocate(Vector2d curr, Vector2d target) {
        Iterator<IMapElement> it = elements.iterator();
        IMapElement tmp = null;
        while (it.hasNext()) {
            tmp = it.next();
            if (tmp.getPosition().equals(curr)) {
                it.remove();
                break;
            }
        }
        if (tmp == null)
            return;
        tmp.setPosition(target);
        elements.add(tmp);
    }

    private boolean isInBounds(Vector2d position) {
        return (position.precedes(upperRight) && position.follows(lowerLeft));
    }

}
