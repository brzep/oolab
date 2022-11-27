package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final VBox vbox;
    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(element.toPath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label(element.toString());
        vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getVbox() {
        return vbox;
    }
}
