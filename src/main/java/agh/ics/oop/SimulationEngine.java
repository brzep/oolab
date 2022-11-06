package agh.ics.oop;

public class SimulationEngine implements IEngine{

    private final MoveDirection[] moves;
    private final Animal[] animals;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        animals = new Animal[positions.length];
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
        this.map = map;
        System.out.println(map);
    }

    public void run() {
        for (int i = 0; i < moves.length; i++) {
            animals[i % animals.length].move(moves[i]);
        }
    }

}
