package agh.ics.oop;


import static java.lang.System.out;

public class SimulationEngine implements IEngine, Runnable {

    private MoveDirection[] moves;
    private final Animal[] animals;
    final int moveDelay;

    IPositionChangeObserver observer;
    public SimulationEngine(MoveDirection[] moves, AbstractWorldMap map, Vector2d[] positions, IPositionChangeObserver observer, int moveDelay) {
        this.moves = moves;
        animals = new Animal[positions.length];
        this.moveDelay = moveDelay;
        this.observer = observer;
        for (int i = 0; i < positions.length; i++) {
            animals[i] = new Animal(map, positions[i]);
            animals[i].addObserver(map);
            map.place(animals[i]);
        }
    }

    public SimulationEngine(AbstractWorldMap map, Vector2d[] positions, IPositionChangeObserver observer, int moveDelay) {
        this(null, map, positions, observer, moveDelay);
    }

    public SimulationEngine(MoveDirection[] moves, AbstractWorldMap map, Vector2d[] positions) {
        this(moves, map, positions, null, 300);
    }

    public void run() {
        for (int i = 0; i < moves.length; i++) {
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException ex){
                out.println(ex.getMessage());
                System.exit(1);
            }
            Vector2d oldPos = animals[i % animals.length].getPosition();
            animals[i % animals.length].move(moves[i]);
            if (observer != null)
                observer.positionChanged(oldPos, animals[i % animals.length].getPosition());
        }
    }

    public void setDirections(MoveDirection[] directions) {
        moves = directions;
    }

}
