package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void run(Direction[] args) {

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case FORWARD:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case LEFT:
                    out.println("Zwierzak skręca w lewo");
                    break;
                case RIGHT:
                    out.println("Zwierzak skręca w prawo");
                    break;
            }
        }
    }

    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println(map);
    }
}