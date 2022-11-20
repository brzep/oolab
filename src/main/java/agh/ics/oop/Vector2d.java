package agh.ics.oop;

import java.util.Objects;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(int n) {
        x = n;
        y = n;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
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

    public int compareX(Vector2d b) {
        if (this.x > b.x)
            return 1;
        else if (this.x == b.x)
            if (this.y > b.y)
                return 1;
            else if (this.y == b.y)
                return 0;
            else
                return -1;
        else
            return -1;
    }

    public int compareY(Vector2d b) {
        if (this.y > b.y)
            return 1;
        else if (this.y == b.y)
            if (this.x > b.x)
                return 1;
            else if (this.x == b.x)
                return 0;
            else
                return -1;
        else
            return -1;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}