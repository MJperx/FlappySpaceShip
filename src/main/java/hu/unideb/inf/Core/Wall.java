package hu.unideb.inf.Core;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * The {@code Wall} class creates a pipe with a specified height and orientation.
 * @author MJ
 */
public class Wall extends Pane{
    /** Width of the pipe. */
    private static int pipeWidth = 50;

    private static final String pipe_0_URL = "pipe_0.png";
    private static final String pipe_1_URL = "pipe_1.png";
    private Rectangle rect;
    private int height;
    private int orientation;

    /**
     * Creates a new instance of {@code Wall} with a given height and orientation.
     * @param height height of the pipe
     * @param orientation Orientation of the pipe
     */
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

        getChildren().add(rect);
    }

    /**
     * {@link Wall#height}.
     * @return the height of the wall
     */
    public int getWallHeight() {
        return height;
    }

    /**
     * {@link Wall#orientation}.
     * @return the orientation of the wall
     */
    public int getOrientation() {
        return orientation;
    }
}
