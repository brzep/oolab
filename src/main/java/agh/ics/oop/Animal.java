package agh.ics.oop;

public class Animal {
    public MapDirection orientation = MapDirection.NORTH;
    public Vector2d position;

    private final IWorldMap map;

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
        Vector2d tmpPosition = this.position;
        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:
                tmpPosition = tmpPosition.add(switch (this.orientation) {
                    case NORTH -> new Vector2d(0, 1);
                    case EAST -> new Vector2d(1, 0);
                    case SOUTH -> new Vector2d(0, -1);
                    case WEST -> new Vector2d(-1, 0);
                });
                break;

            case BACKWARD:
                tmpPosition = tmpPosition.add(switch (this.orientation) {
                    case NORTH -> new Vector2d(0, -1);
                    case EAST -> new Vector2d(-1, 0);
                    case SOUTH -> new Vector2d(0, 1);
                    case WEST -> new Vector2d(1, 0);
                });
                break;
        }

        if (tmpPosition != position && map.canMoveTo(tmpPosition)) {
            map.relocate(position, tmpPosition);
            position = tmpPosition;
        }

    }

}
