package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

     @Test
    void testEquals() {
         Vector2d[] v1 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(-1, -1)};
         Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 1), new Vector2d(1, 1)};
         boolean[] ans = {true, true, false, false};
         for (int i = 0; i < 4; i++) {
             assertEquals(ans[i], v1[i].equals(v2[i]));
         }
     }

     @Test
    void testToString() {
         Vector2d[] v = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(-1, -1)};
         String[] s = {"(1, 1)", "(1, 2)", "(1, 1)", "(1, 1)"};
         boolean[] ans = {true, true, false, false};
         for (int i = 0; i < 4; i++) {
             assertEquals(ans[i], v[i].toString().equals(s[i]));
         }
     }

     @Test
    void testPrecedes() {
         Vector2d v1 = new Vector2d(1, 1);
         Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 0), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(2, 1), new Vector2d(2, 2)};
         boolean[] ans = {true, true, false, false, false, false, false, true, true};
         for (int i = 0; i < 9; i++) {
             assertEquals(ans[i], v1.precedes(v2[i]));
         }
     }

    @Test
    void testFollows() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 0), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(2, 1), new Vector2d(2, 2)};
        boolean[] ans = {true, false, true, true, true, false, false, false, false};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1.follows(v2[i]));
        }
    }

    @Test
    void testUpperRight() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 0), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(2, 1), new Vector2d(2, 2)};
        Vector2d[] ans = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(2, 1), new Vector2d(2, 2)};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1.upperRight(v2[i]));
        }
    }

    @Test
    void testLowerLeft() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 0), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(2, 1), new Vector2d(2, 2)};
        Vector2d[] ans = {new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 0), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(0, 1), new Vector2d(1, 0), new Vector2d(1, 1), new Vector2d(1, 1)};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1.lowerLeft(v2[i]));
        }
    }

    @Test
    void testAdd() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(-1, 0), new Vector2d(0, 0), new Vector2d(-100, 100), new Vector2d(0, 2), new Vector2d(-2, -100), new Vector2d(200, 0), new Vector2d(2, -20)};
        Vector2d[] ans = {new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(0, 1), new Vector2d(1, 1), new Vector2d(-99, 101), new Vector2d(1, 3), new Vector2d(-1, -99), new Vector2d(201, 1), new Vector2d(3, -19)};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1.add(v2[i]));
        }
    }

    @Test
    void testSubtract() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d[] v2 = {new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(-1, 0), new Vector2d(0, 0), new Vector2d(-100, 100), new Vector2d(0, 2), new Vector2d(-2, -100), new Vector2d(200, 0), new Vector2d(2, -20)};
        Vector2d[] ans = {new Vector2d(0, 0), new Vector2d(0, -1), new Vector2d(2, 1), new Vector2d(1, 1), new Vector2d(101, -99), new Vector2d(1, -1), new Vector2d(3, 101), new Vector2d(-199, 1), new Vector2d(-1, 21)};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1.subtract(v2[i]));
        }
    }

    @Test
    void testOpposite() {
        Vector2d[] v1 = {new Vector2d(1, 1), new Vector2d(1, 0), new Vector2d(1, -1), new Vector2d(0, 1), new Vector2d(0, 0), new Vector2d(0, -1), new Vector2d(-1, 1), new Vector2d(-1, 0), new Vector2d(-1, -1)};
        Vector2d[] ans = {new Vector2d(-1, -1), new Vector2d(-1, 0), new Vector2d(-1, 1), new Vector2d(0, -1), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(1, -1), new Vector2d(1, 0), new Vector2d(1, 1)};
        for (int i = 0; i < 9; i++) {
            assertEquals(ans[i], v1[i].opposite());
        }
    }
}