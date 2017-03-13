package hu.unideb.inf.Core;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Ship extends Pane {

    public static int width = 30;
    public static int height = 30;

    public Point2D velocity;
    Rectangle rect;

    public Ship() {
        Image img = new Image(getClass().getResource("/images/spaceShip.png").toExternalForm());
        rect = new Rectangle(width, height);
        rect.setFill(new ImagePattern(img, 0, 0, 1, 1, true));
        velocity = new Point2D(0,0);
        setTranslateX(100);
        setTranslateY(300);
        getChildren().addAll(rect);
    }

    public void moveX(int value) {
        for (int i = 0; i < value; i++) {
            for (Wall w : Main.walls ) {
                if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (getTranslateX() + width == w.getTranslateX()) {
                        Main.running = false;
                        Main.failGame = true;
                        setTranslateX(getTranslateX()-1);
                        return;
                    }
                }

                if (getTranslateX() + width == w.getTranslateX()) {
                    Main.score = Main.score + 1;
                }
            }

            setTranslateX(getTranslateX()+1);
        }
    }

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

    public void jump() {
        velocity = new Point2D(3,-15);
    }

    public void shipNull(){
        velocity.add(0,0);
        setTranslateX(100);
        setTranslateY(300);
    }
}
