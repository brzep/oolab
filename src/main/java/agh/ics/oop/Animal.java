package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement {
    public MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;
    List<IPositionChangeObserver> observerList = new ArrayList<>();

    public Animal() {
        this.position = new Vector2d(2,2);
    }
    public Animal(IWorldMap map) {
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.position = initialPosition;
        this.map = map;
    }

    public String toString() {
        return switch (this.orientation) {
            case WEST -> "W";
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
        };
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = this.position;
        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:
                newPosition = newPosition.add(switch (this.orientation) {
                    case NORTH -> new Vector2d(0, 1);
                    case EAST -> new Vector2d(1, 0);
                    case SOUTH -> new Vector2d(0, -1);
                    case WEST -> new Vector2d(-1, 0);
                });
                break;

            case BACKWARD:
                newPosition = newPosition.add(switch (this.orientation) {
                    case NORTH -> new Vector2d(0, -1);
                    case EAST -> new Vector2d(-1, 0);
                    case SOUTH -> new Vector2d(0, 1);
                    case WEST -> new Vector2d(1, 0);
                });
                break;
        }

        if (newPosition != position && map.canMoveTo(newPosition)) {
            positionChanged(position, newPosition);
            position = newPosition;
//            map.relocate(position, newPosition);
        }

    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
    void addObserver(IPositionChangeObserver observer) {
        observerList.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        observerList.remove(observer);
    }

}
