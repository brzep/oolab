package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.*;

import static java.lang.System.out;

public class World {

    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        } catch(IllegalArgumentException ex) {
            out.println(ex.getMessage());
            System.exit(1);
        }
    }
}