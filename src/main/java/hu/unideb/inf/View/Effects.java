package hu.unideb.inf.View;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Effect class. Implements many effects for a better visual content.
 */
public class Effects {

    /**
     * Map t....
     *
     * @param value The value.
     *
     * @param targetStart Start.
     *
     * @return The mapped value.
     */
    private static double map(double value, double targetStart) {
        return targetStart + (1 - targetStart) * ((value - 0) / 360);
    }

    /** ScaleTransition. */
    private ScaleTransition st = new ScaleTransition();

    /**
     * Play scale effect on a label.
     *
     * @param label A label.
     */
    public void scaleEffect(Label label){
        st.setDuration(Duration.millis(200));
        st.setNode(label);
        st.setFromX(1.0);
        st.setFromY(1.0);
        st.setByX(1.01);
        st.setByY(1.01);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }

    /**
     * Generates a random ColorAdjust the a ship.
     *
     * @return The new color.
     */
    public static ColorAdjust randomColor(){
        ColorAdjust blackout = new ColorAdjust();
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        double hue = map( (color.getHue() + 180) % 360, -1);
        blackout.setHue(hue);

        return blackout;
    }

    /**
     * Generates a random color.
     *
     * @return A new color.
     */
    public static Color randomTextColor(){
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        return color;
    }
}
