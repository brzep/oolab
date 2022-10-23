package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    private Vector2d upperRightBound = new Vector2d(4, 4);
    private Vector2d lowerLeftBound = new Vector2d(0, 0);

    public String toString() {
        return "pozycja zwierzaka " + this.position.toString() + ", orientacja zwierzaka: " + this.orientation.toString();
    }

    public boolean isAt(Vector2d pos) {
        return this.position.equals(pos);
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

        if (tmpPosition.precedes(upperRightBound) && tmpPosition.follows(lowerLeftBound)) //w przypadku kontroli zajętości pól dodaję do warunku "&& World.setOccupation(tmpPosition)"
            this.position = tmpPosition;
    }

}
