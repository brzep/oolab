package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void testRebounding() {
        GrassField map = new GrassField(1);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        Animal[] animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }

        assertEquals(animals[0], map.objectAt(new Vector2d(2, 5)));
        assertEquals(MapDirection.NORTH, animals[0].orientation);
        assertEquals(animals[1], map.objectAt(new Vector2d(3, 3)));
        assertEquals(MapDirection.NORTH, animals[1].orientation);
        assertEquals(map.elements.size(), 3);
    }

    @Test
    void testCollision() {
        GrassField map = new GrassField(3);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        Animal[] animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }

        assertEquals(animals[0], map.objectAt(new Vector2d(2, 3)));
        assertEquals(MapDirection.EAST, animals[0].orientation);
        assertEquals(animals[1], map.objectAt(new Vector2d(3, 3)));
        assertEquals(MapDirection.WEST, animals[1].orientation);
        assertEquals(5, map.elements.size());
    }

    @Test
    void testGoingOutside() {
        GrassField map = new GrassField(3);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 3)};
        MoveDirection[] moves = {MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD};
        Animal[] animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }
        System.out.println(map);
        assertEquals(animals[0], map.objectAt(new Vector2d(2, 0)));
        assertEquals(MapDirection.NORTH, animals[0].orientation);
        assertEquals(animals[1], map.objectAt(new Vector2d(0, 3)));
        assertEquals(MapDirection.WEST, animals[1].orientation);
        assertEquals(5, map.elements.size());
    }
}