package hu.unideb.inf.Core;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Wall extends Pane{

    public static int pipeWidth = 50;

    private static final String pipe_0_URL = "pipe_0.png";
    private static final String pipe_1_URL = "pipe_1.png";

    private Rectangle rect;
    public int height;
    public int orientation;

    public Wall(int height, int orientation) {

        this.height = height;
        this.orientation = orientation;

        Image img_0 = new Image(getClass().getResourceAsStream(pipe_0_URL));
        Image img_1 = new Image(getClass().getResourceAsStream(pipe_1_URL));

        rect = new Rectangle(pipeWidth, height);

        if (orientation == 0){
            rect.setFill(new ImagePattern(img_0, 0, 0, 1, 1, true));
        } else if (orientation == 1){
            rect.setFill(new ImagePattern(img_1, 0, 0, 1, 1, true));
        }

        /*if (number > 1 && number < 10) {
            rect.setRotationAxis(Rotate.Z_AXIS);
            rect.setRotate(-25 + 50 * Math.random());
            rect.setTranslateY(-40);
        }*/

        getChildren().add(rect);
    }
}