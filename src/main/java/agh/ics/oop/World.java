package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.GuiElementBox;
import javafx.application.Application;

import java.io.FileNotFoundException;

import static java.lang.System.out;

public class World {

    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
            GuiElementBox test = new GuiElementBox(new Grass(new Vector2d(0)));
        } catch(IllegalArgumentException ex) {
            out.println(ex.getMessage());
            System.exit(1);
        } catch (FileNotFoundException ex) {
            out.println(ex.getMessage());
            System.exit(1);
        }
    }
}