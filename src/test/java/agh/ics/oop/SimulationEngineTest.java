package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void test1() {
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 9\n" +
                "  5: ---------------------\n" +
                "  4: | | |N| |E| | | | | |\n" +
                "  3: | | | | | | | | | | |\n" +
                "  2: | | | | | | | | | | |\n" +
                "  1: | | | | | | | | | | |\n" +
                "  0: | | | | | | | | | | |\n" +
                " -1: ---------------------\n", map.toString());
    }

    @Test
    void test2() {
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.BACKWARD};
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 9\n" +
                "  5: ---------------------\n" +
                "  4: | | |S|E| | | | | | |\n" +
                "  3: | | | | | | | | | | |\n" +
                "  2: | | | | | | | | | | |\n" +
                "  1: | | | | | | | | | | |\n" +
                "  0: | | | | | | | | | | |\n" +
                " -1: ---------------------\n", map.toString());
    }

    @Test
    void test3() {
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 9\n" +
                "  5: ---------------------\n" +
                "  4: |W|E| | | | | | | | |\n" +
                "  3: | | | | | | | | | | |\n" +
                "  2: | | | | | | | | | | |\n" +
                "  1: | | | | | | | | | | |\n" +
                "  0: | | | | | | | | | | |\n" +
                " -1: ---------------------\n", map.toString());
    }


}