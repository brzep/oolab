package agh.ics.oop;

public class Grass extends AbstractWorldMapElement{

    public Grass(Vector2d position) {
        this.position = position;
    }

    public String toString() {
        return "*";
    }

    public String toPath() {
        return "./src/main/resources/grass.png";
    }

    public String getTexturePath() {
        return "resources/grass.png";
    }

}