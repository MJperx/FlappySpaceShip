package hu.unideb.inf.Core;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Medal extends Pane{
    private static int size = 40;
    Rectangle rectangle;

    public Medal() {
        rectangle = new Rectangle(size,size);
        Image img_med = new Image(getClass().getResourceAsStream("medal.gif"));
        rectangle.setFill(new ImagePattern(img_med, 0, 0, 1, 1, true));

        getChildren().addAll(rectangle);
    }
}
