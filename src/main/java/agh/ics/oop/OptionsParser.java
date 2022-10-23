package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] strings) {
        MoveDirection[] results = new MoveDirection[strings.length];

        int i = 0;
        for (String s: strings) {
            switch (s) {
                case "f":
                case "forward":
                    results[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "b":
                case "backward":
                    results[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                case "l":
                case "left":
                    results[i] = MoveDirection.LEFT;
                    i++;
                    break;
                case "r":
                case "right":
                    results[i] = MoveDirection.RIGHT;
                    i++;
                    break;
            };
        }
        System.arraycopy(results, 0, results, 0, i);
        return results;
    }
}
