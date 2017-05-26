package hu.unideb.inf.Core;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code Ship} class create a rectangle.
 * @author MJ
 */
public class Ship extends Pane {

    /** Logger for logging.*/
    private static Logger logger = LoggerFactory.getLogger( Ship.class );

    /** Width of the ship. */
    public static int width = 30;

    /** Height of the ship. */
    public static int height = 30;

    /** Velocity of the ship. */
    public Point2D velocity;
    Rectangle rect;

    /**
     * Creates an instance of the {@code Ship} class and
     * sets the main propeties of the rectangle.
     */
    public Ship() {
        logger.debug("Creating ship...");
        rect = new Rectangle(width, height);
        velocity = new Point2D(0,0);
        setTranslateX(100);
        setTranslateY(300);
        getChildren().add(rect);
        logger.debug("Ship created.");
    }

    /**
     * Moves the ship to the specified location in the x axis.
     * @param value the X coordinate of the new location
     */
    public void moveX(int value) {
        for (int i = 0; i < value; i++) {
            for (Wall w : Main.walls) {
                if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (getTranslateX() + width == w.getTranslateX()) {
                        Main.running = false;
                        Main.failGame = true;
                        setTranslateX(getTranslateX()+1);
                        return;
                    }
                }
            }

            for (Wall w : Main.walls2){
                if (getTranslateX()== w.getTranslateX()) {
                    Main.score++;
                }
            }
            setTranslateX(getTranslateX()+1);
        }
    }

    /**
     * Moves the ship to the specified location in the Y axis.
     * @param value the Y coordinate of the new location
     */
    public void moveY(int value) {
        boolean moveDown = value >0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall w : Main.walls) {
                if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (moveDown) {
                        setTranslateY(getTranslateY()-1);
                        break;
                    } else {
                        setTranslateY(getTranslateY()+1);
                        return;
                    }
                }
            }

            if (getTranslateY() < 0) {
                setTranslateY(0);
            }

            if (getTranslateY() > Main.winHeight - rect.getHeight()){
                Main.running = false;
                Main.failGame = true;
                setTranslateY(Main.winHeight - rect.getHeight());
            }

            setTranslateY(getTranslateY() + (moveDown?1:-1));
        }
    }

    /** Set the velocity of the ship. */
    public void jump() {
        velocity = new Point2D(3,-15);
    }

    /** Set the ship to the original position. */
    public void shipNull(){
        velocity.add(0,0);
        setTranslateX(100);
        setTranslateY(300);
    }
}
