//package agh.ics.oop;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class WorldTest {
//
//    @Test
//    void testStartParameters() {
//        Animal testSubject = new Animal();
//        assertEquals("pozycja zwierzaka (2, 2), orientacja zwierzaka: Północ", testSubject.toString());
//    }
//
//    @Test
//    void testMoves() {
//        MoveDirection[] orders = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
//        MapDirection[] orientations = {MapDirection.NORTH, MapDirection.NORTH, MapDirection.WEST, MapDirection.NORTH, MapDirection.WEST, MapDirection.WEST, MapDirection.WEST};
//        Vector2d[] positions = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(1, 4), new Vector2d(2, 4)};
//        Animal testSubject = new Animal();
//        for (int i = 0; i < orders.length; i++) {
//            testSubject.move(orders[i]);
//            assertEquals("pozycja zwierzaka " + positions[i] + ", orientacja zwierzaka: " + orientations[i].toString(), testSubject.toString());
//        }
//    }
//
//    @Test
//    void testOutside() {
//        MoveDirection[] orders = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD};
//        MapDirection[] orientations = {MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.WEST, MapDirection.WEST, MapDirection.WEST, MapDirection.WEST};
//        Vector2d[] positions = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(4, 4)};
//        Animal testSubject = new Animal();
//        for (int i = 0; i < orders.length; i++) {
//            testSubject.move(orders[i]);
//            assertEquals("pozycja zwierzaka " + positions[i] + ", orientacja zwierzaka: " + orientations[i].toString(), testSubject.toString());
//        }
//    }
//
//    @Test
//    void testInputInterpreter() {
//        String[] input = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
//        MoveDirection[] ans = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
//        OptionsParser parser = new OptionsParser();
//        assertArrayEquals(ans, parser.parse(input));
//    }
//}