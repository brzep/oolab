package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import static java.lang.System.out;

public class App extends Application implements IPositionChangeObserver {
    GridPane grid = new GridPane();
    AbstractWorldMap map;
    SimulationEngine engine;
    Stage primaryStage;
    int cellSize = 40;

    private void renderMap(AbstractWorldMap map) throws FileNotFoundException {
        grid = new GridPane();
        grid.setGridLinesVisible(true);

        Label topLeft = new Label("y\\x");
        grid.add(topLeft, 0, 0);
        GridPane.setHalignment(topLeft, HPos.CENTER);
        GridPane.setValignment(topLeft, VPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        grid.getRowConstraints().add(new RowConstraints(cellSize));

        for (int i = 0; i <= map.upperRight.x; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
            Label lbl = new Label(Integer.toString(i));
            grid.add(lbl, i + 1, 0);
            GridPane.setHalignment(lbl, HPos.CENTER);
            GridPane.setValignment(lbl, VPos.CENTER);
        }
        for (int i = 0; i <= map.upperRight.y; i++) {
            grid.getRowConstraints().add(new RowConstraints(cellSize));
            Label lbl = new Label(Integer.toString(map.upperRight.y-  i));
            grid.add(lbl, 0, i + 1);
            GridPane.setHalignment(lbl, HPos.CENTER);
            GridPane.setValignment(lbl, VPos.CENTER);
        }

        for (int i = 0; i <= map.upperRight.x; i++) {
            for (int j = 0; j <= map.upperRight.y; j++) {
                if (map.isOccupied(new Vector2d(i, map.upperRight.y - j))) {
                    VBox box = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(i, map.upperRight.y - j))).getVbox();
                    grid.add(box, i + 1, j + 1);
                    GridPane.setHalignment(box, HPos.CENTER);
                    GridPane.setValignment(box, VPos.CENTER);
                }
            }
        }
        Button button = new Button("go");
        TextField textField = new TextField();
        HBox hbox = new HBox(textField, button);
        VBox vbox = new VBox(grid, hbox);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(actionEvent -> {
            OptionsParser parser = new OptionsParser();
            engine.setDirections(parser.parse(textField.getText().split(" ")));
            Thread thread = new Thread(engine);
            thread.start();
        });
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            try {
                renderMap(map);
            } catch (FileNotFoundException ex) {
                out.println(ex.getMessage());
                System.exit(1);
            }
        });

    }

    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        renderMap(map);
    }

    public void init() {
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(21, 2), new Vector2d(3, 17), new Vector2d(0, 0)};
        engine = new SimulationEngine(directions, map, positions, this, 300);
    }

}
