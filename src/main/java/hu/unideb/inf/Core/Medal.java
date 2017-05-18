package hu.unideb.inf.Core;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Medal class. If you catch a medal you get extra score.
 */
public class Medal extends Pane{
    private static int size = 40;
    private Rectangle rectangle;

    /** Constructor. */
    public Medal() {
        rectangle = new Rectangle(size,size);
        Image img_med = new Image(getClass().getResourceAsStream("medal.gif"));
        rectangle.setFill(new ImagePattern(img_med, 0, 0, 1, 1, true));

        getChildren().addAll(rectangle);
    }
}
