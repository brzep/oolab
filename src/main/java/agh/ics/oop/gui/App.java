package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import static java.lang.System.out;

public class App extends Application {



    public void start(Stage primaryStage) {
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        AbstractWorldMap map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(41, 2), new Vector2d(3, 17), new Vector2d(0, 0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println(map);


        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        int cellSize = 25;

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
                    Label lbl = new Label(map.objectAt(new Vector2d(i, map.upperRight.y - j)).toString());
                    grid.add(lbl, i + 1, j + 1);
                    GridPane.setHalignment(lbl, HPos.CENTER);
                    GridPane.setValignment(lbl, VPos.CENTER);
                }
            }
        }
        Scene scene = new Scene(grid, cellSize * (map.upperRight.x + 2), cellSize * (map.upperRight.y + 2));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
