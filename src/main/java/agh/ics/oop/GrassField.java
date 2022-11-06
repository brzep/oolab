package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final int minBound;

    public GrassField(int fieldsQuantity) {
        lowerLeft = new Vector2d(0, 0);
        Random randGenerator = new Random();
        this.minBound = (int) Math.floor(Math.sqrt(fieldsQuantity * 10));
        upperRight = new Vector2d(minBound, minBound);
        for (int i = 0; i < fieldsQuantity; i++) {
            Vector2d pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
            while (!isFreeGrass(pos))
                pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
            if (pos.follows(upperRight))
                upperRight = pos;
            elements.add(new Grass(pos));
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return (isFreeAnimal(position) && position.follows(lowerLeft));
    }

    public boolean place(Animal animal) {
        if (this.isFreeAnimal(animal.getPosition())) {
            elements.add(animal);
            grassReplacement(animal.getPosition());
            if (animal.position.follows(upperRight))
                upperRight = animal.getPosition();
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied(Vector2d position) {
        return !(this.isFreeAnimal(position) && this.isFreeGrass(position) && position.follows(lowerLeft));
    }

    public void relocate(Vector2d curr, Vector2d target) {
        Iterator<IMapElement> it = elements.iterator();
        IMapElement tmp = null;
        while (it.hasNext()) {
            tmp = it.next();
            if (tmp.getPosition().equals(curr)) {
                it.remove();
                updateBound();
                break;
            }
        }
        if (tmp == null)
            return;
        tmp.setPosition(target);
        elements.add(tmp);
        updateBound();
        grassReplacement(target);
    }

    @Override
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

    private void updateBound() {
        Vector2d max = new Vector2d(minBound, minBound);
        for (IMapElement element : elements) {
            max = new Vector2d(Math.max(max.x, element.getPosition().x), Math.max(max.y, element.getPosition().y));
        }
        upperRight = max;
    }

    private boolean isFreeGrass(Vector2d position) {
        Iterator<IMapElement> it = elements.iterator();
        IMapElement tmp;
        while (it.hasNext()) {
            tmp = it.next();
            if (tmp.getPosition().equals(position) && tmp.getClass() == Grass.class)
                return false;
        }
        return true;
    }

    private void grassReplacement(Vector2d position) {  //spawn new Grass object if there is already one on "position"
        Iterator<IMapElement> it = elements.iterator();
        IMapElement tmp;
        boolean found = false;
        while (it.hasNext()) {
            tmp = it.next();
            if (tmp.getPosition().equals(position) && tmp.getClass() == Grass.class) {
                it.remove();
                found = true;
                break;
            }
        }
        if (!found)
            return;
        Random randGenerator = new Random();
        Vector2d pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
        while (isOccupied(pos)) {   //mam swiadomosc, ze ten while moze potencjalnie dzialac w nieskonczosc, wymaga to poprawy, jednak niestety nie mialem juz na to czasu
            pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
        }
        elements.add(new Grass(pos));

    }

}
