package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final int minBound;     //ensures that map is not smaller than sqrt(10*fieldsQuantity)
    MapBoundary boundary;

    public GrassField(int fieldsQuantity) {
        minBound = (int) Math.floor(Math.sqrt(fieldsQuantity * 10));
        boundary = new MapBoundary(new Vector2d (minBound));
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(minBound);

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

    public void place(Animal animal) {
        if (this.isFreeAnimal(animal.getPosition())) {  //an animal can be placed on certain position if there are no other animals
            if (elements.get(animal.getPosition()) == null) {   //if there's nothing on that position
                 elements.put(animal.getPosition(), animal);
            } else {    //there's already grass on that position
                addGrass();     //animal eats it, so we need to add new grass to map
                elements.replace(animal.getPosition(), animal);     //and animal is placed on desired position
            }
            boundary.add(animal);
            upperRight = boundary.getBound();
        } else {
            throw new IllegalArgumentException("sorry, position " + animal.getPosition() + " is already occupied by another animal");
        }
    }

    public boolean isOccupied(Vector2d position) {
        return !(this.isFreeAnimal(position) && this.isFreeGrass(position) && position.follows(lowerLeft));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (elements.get(newPosition) != null) {
            addGrass();
            boundary.remove(newPosition, Grass.class);
            elements.replace(newPosition, elements.remove(oldPosition));
        } else
            elements.put(newPosition, elements.remove(oldPosition));
        boundary.positionChanged(oldPosition, newPosition);
        upperRight = boundary.getBound();
    }

    private boolean isFreeGrass(Vector2d position) {
        IMapElement tmp = elements.get(position);
        if (tmp == null)
            return true;
        return !(tmp.getClass() == Grass.class);
    }

    private void addGrass() {  //spawn new Grass object from
        Random randGenerator = new Random();
        Vector2d bound = boundary.getBound();
        Vector2d pos = new Vector2d(randGenerator.nextInt(bound.x), randGenerator.nextInt(bound.y));
        while (isOccupied(pos)) {
            pos = new Vector2d(randGenerator.nextInt(bound.x), randGenerator.nextInt(bound.y));
        }
        Grass tmp = new Grass(pos);
        elements.put(pos, tmp);
        boundary.add(tmp);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, boundary.getBound());
    }

}
