package agh.ics.oop;

import java.util.Vector;

abstract public class AbstractWorldMapElement implements IMapElement{

    protected Vector2d position;
    public Vector2d getPosition() {
        return this.position;
    }
    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
