//package agh.ics.oop;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MapDirectionTest {
//
//    @Test
//    void testNext() {
//        MapDirection[] dirs = {MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH};
//        MapDirection[] ans = {MapDirection.SOUTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST};
//        for (int i = 0; i < 4; i++) {
//            assertEquals(ans[i], dirs[i].next());
//        }
//    }
//
//    @Test
//    void testPrevious() {
//        MapDirection[] dirs = {MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH};
//        MapDirection[] ans = {MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH, MapDirection.EAST};
//        for (int i = 0; i < 4; i++) {
//            assertEquals(ans[i], dirs[i].previous());
//        }
//    }
//
//}