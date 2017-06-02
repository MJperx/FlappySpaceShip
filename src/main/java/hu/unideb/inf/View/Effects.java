package hu.unideb.inf.View;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * The {@code Effect} class implements many effects for a better visual content.
 * @author MJ
 */
public class Effects {

    /**
     * The Color.getHue() returns a value between 0-360.
     * This method transform the value between 0-1.
     *
     * @param value The value.
     * @param targetStart Start.
     * @return The transformed value.
     */
    private static double map(double value, double targetStart) {
        return targetStart + (1 - targetStart) * (value / 360);
    }

    /** Creates an empty {@link ScaleTransition}. */
    private ScaleTransition st = new ScaleTransition();

    /**
     * Play scale effect on a {@link Label}.
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
     * Generates a random {@link ColorAdjust} the a ship.
     * @return The new color for the ship.
     */
    public static ColorAdjust randomColor(){
        ColorAdjust blackout = new ColorAdjust();
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        System.out.println("color hue: " + color.getHue());
        double hue = map( (color.getHue() + 180) % 360, -1);
        System.out.println("hue: " + hue);
        blackout.setHue(hue);
        return blackout;
    }

    /**
     * Generates a random {@link Color}.
     * @return A new color.
     */
    public static Color randomTextColor(){
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        return color;
    }
}
