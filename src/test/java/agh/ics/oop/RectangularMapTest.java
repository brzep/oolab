package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testBounds() {
        RectangularMap map = new RectangularMap(4, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 2)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        Animal[] animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }

        assertEquals(" y\\x  0 1 2 3\n" +
                "  5: ---------\n" +
                "  4: | | |N| |\n" +
                "  3: | | | | |\n" +
                "  2: | | | | |\n" +
                "  1: | | | | |\n" +
                "  0: | | | |N|\n" +
                " -1: ---------\n", map.toString());
    }

    @Test
    void testCollision() {
        RectangularMap map = new RectangularMap(4, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        Animal[] animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }

        assertEquals(" y\\x  0 1 2 3\n" +
                "  5: ---------\n" +
                "  4: | | | | |\n" +
                "  3: | | |E|W|\n" +
                "  2: | | | | |\n" +
                "  1: | | | | |\n" +
                "  0: | | | | |\n" +
                " -1: ---------\n", map.toString());
    }

    @Test
    void testDuplicates() {
        RectangularMap map = new RectangularMap(3, 3);
        Animal[] animals = {new Animal(map, new Vector2d(1, 1)), new Animal(map, new Vector2d(1, 1))};
        map.place(animals[0]);
        assertFalse(map.place(animals[1]));
    }
}