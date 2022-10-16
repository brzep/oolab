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
                case UNKNOWN:
                    out.println("Zwierzak nie wie co robić");
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Direction[] dirs = new Direction[args.length];

        for(int i = 0; i < args.length; i++) {
            dirs[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> Direction.UNKNOWN;
            };
        }

        out.println("Start");
        run(dirs);
        out.println("Stop");
    }
}