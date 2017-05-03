package hu.unideb.inf.View;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Effects {

    public static double map(double value, double start, double stop, double targetStart, double targetStop) {
        return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
    }

    public ScaleTransition st = new ScaleTransition();

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

    public static ColorAdjust randomColor(){
        ColorAdjust blackout = new ColorAdjust();
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        double hue = map( (color.getHue() + 180) % 360, 0, 360, -1, 1);
        blackout.setHue(hue);

        return blackout;
    }

    public static Color randomTextColor(){
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        return color;
    }
}
