package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final int minBound;     //ensures that map is not smaller than sqrt(10*fieldsQuantity)

    public GrassField(int fieldsQuantity) {
        lowerLeft = new Vector2d(0, 0);
        this.minBound = (int) Math.floor(Math.sqrt(fieldsQuantity * 10));
        upperRight = new Vector2d(minBound, minBound);

        Random randGenerator = new Random();
        for (int i = 0; i < fieldsQuantity; i++) {
            Vector2d pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
            while (!isFreeGrass(pos))   //finding position with no grass on it
                pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
            elements.put(pos, new Grass(pos));
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return (isFreeAnimal(position) && position.follows(lowerLeft));
    }

    public boolean place(Animal animal) {
        if (this.isFreeAnimal(animal.getPosition())) {  //an animal can be placed on certain position if there are no other animals
            if (elements.get(animal.getPosition()) == null) {   //if there's nothing on that position
                 elements.put(animal.getPosition(), animal);
            } else {    //there's already grass on that position
                addGrass();     //animal eats it, so we need to add new grass to map
                elements.replace(animal.getPosition(), animal);     //and animal is placed on desired position
            }
            updateBound(animal.getPosition());   //updating upperRight map bound
            return true;    //everything went fine
        } else {
            return false;   //animal couldn't be placed
        }
    }

    public boolean isOccupied(Vector2d position) {
        return !(this.isFreeAnimal(position) && this.isFreeGrass(position) && position.follows(lowerLeft));
    }

    public void relocate(Vector2d previous, Vector2d target) {
        IMapElement tmp = elements.remove(previous);
        tmp.setPosition(target);
        if (elements.get(target) == null) {
            elements.put(target, tmp);
        } else {
            elements.replace(target, tmp);
            addGrass();
        }
        updateBoundAll();   //we need to check all elements, because previous could be the bound and now it is removed
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (elements.get(newPosition) != null) {
            addGrass();
            elements.replace(newPosition, elements.remove(oldPosition));
        } else
            elements.put(newPosition, elements.remove(oldPosition));
    }

    private void updateBound(Vector2d position) {
        upperRight = new Vector2d(Math.max(upperRight.x, position.x), Math.max(upperRight.y, position.y));
    }

    private void updateBoundAll() {
        Vector2d max = new Vector2d(minBound, minBound);
        for (Vector2d element : elements.keySet()) {
            max = new Vector2d(Math.max(max.x, element.x), Math.max(max.y, element.y));
        }
        upperRight = max;
    }

    private boolean isFreeGrass(Vector2d position) {
        IMapElement tmp = elements.get(position);
        if (tmp == null)
            return true;
        return !(tmp.getClass() == Grass.class);
    }

    private void addGrass() {  //spawn new Grass object from
        Random randGenerator = new Random();
        Vector2d pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
        while (isOccupied(pos)) {
            pos = new Vector2d(randGenerator.nextInt(minBound), randGenerator.nextInt(minBound));
        }
        elements.put(pos, new Grass(pos));

    }

}
