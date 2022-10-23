package agh.ics.oop;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
//            return "(" + String.ValueOf(x) + ", " + String.ValueOf(y) + ")";
        return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
    }

    public boolean precedes(Vector2d other) {
        if(this.x <= other.x && this.y <= other.y)
            return true;
        return false;
    }

    public boolean follows(Vector2d other) {
        if(this.x >= other.x && this.y >= other.y)
            return true;
        return false;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        Vector2d obj = (Vector2d) other;
        if (this.x == obj.x && this.y == obj.y)
            return true;
        return false;
    }


}