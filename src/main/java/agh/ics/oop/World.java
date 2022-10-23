package agh.ics.oop;

import static java.lang.System.out;

public class World {

// ------------------------------------------------------------------------------------------------
// propozycja mechanizmu kontrolującego pojawienie się dwóch zwierząt w jednym miejscu
//
//    private static boolean[][] availability = {{true, true, true, true, true},    //'true' if certain position is free, 'false' if it's busy
//                                      {true, true, true, true, true},
//                                      {true, true, true, true, true},
//                                      {true, true, true, true, true},
//                                      {true, true, true, true, true},
//                                      {true, true, true, true, true}};
//
//
//    public static boolean setOccupation(Vector2d position) {
//        if (availability[position.x][position.y]) {
//            availability[position.x][position.y] = false;
//            return true;    //to let other method know it succeeded
//        }
//        return false;       //as above, but did not succeed
//    }
//
//    public Animal createAnimal() {
//        if (isFree(new Vector2d(2, 2))) {
//            return new Animal();
//        }
//        return null;
//    }
//
// w metodzie 'move' klasy 'Animal' w 48 linijce tuż przed zmianą pozycji wywołuję metodę 'setOccupation' i jeśli zwróci ona 'true' to aktualizuję pozycję obiektu Animal
//
// koniec propozycji
//  ----------------------------------------------------------------------------------
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
//                case UNKNOWN:
//                    out.println("Zwierzak nie wie co robić");
//                    break;
            }
        }
    }


    public static void main(String[] args) {

        Animal animal = new Animal();
        OptionsParser parser = new OptionsParser();
        MoveDirection[] instructions = parser.parse(args);
        out.println(animal.toString());

        for (MoveDirection instr: instructions) {
            animal.move(instr);
            out.println(animal.toString());
        }

    }
}