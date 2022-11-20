package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void testException() {
        OptionsParser parser = new OptionsParser();
        String[] strings = {"f", "f", "b", "r", "l", "k"};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(strings));
        assertEquals("k is not legal move specification", exception.getMessage());
    }

    @Test
    void testResult() {
        OptionsParser parser = new OptionsParser();
        String[] strings = {"f", "b", "r", "l", "forward", "backward", "right", "left"};
        MoveDirection[] results = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals(results, parser.parse(strings));
    }

}