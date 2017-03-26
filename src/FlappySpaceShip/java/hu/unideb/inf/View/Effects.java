package hu.unideb.inf.View;

import hu.unideb.inf.Core.Main;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Effects {

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
}
