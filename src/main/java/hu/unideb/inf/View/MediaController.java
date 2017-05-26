package hu.unideb.inf.View;

import hu.unideb.inf.Core.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static hu.unideb.inf.Core.Main.running;

/**
 * The {@code MediaController} class initialize the used sounds and images.
 * @author MJ
 */
public class MediaController {

    /** {@link Logger} for logging.*/
    private static Logger logger = LoggerFactory.getLogger( Main.class );

    private static final String backgroundURL = "bg2.png";
    private static final String mainMusicURL = "mainMusic.wav";
    private static final String jumpSoundURL = "jumpSound.wav";

    private MediaPlayer mediaPlayer;
    private MediaPlayer jumpPlayer;

    /** The {@link Background} of the game. */
    public Background bg;

    /** Creates an empty instance of {@code MediaController}. */
    public MediaController(){
        init();
    }

    /** Initialize the medias. */
    private void init(){
        Media sound = new Media(getClass().getResource(mainMusicURL).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);

        Media juMedia = new Media(getClass().getResource(jumpSoundURL).toExternalForm());
        jumpPlayer = new MediaPlayer(juMedia);

        Image img = new Image(getClass().getResourceAsStream(backgroundURL));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bg = new Background(bgImg);
    }

    /** Play or pause the background music. */
    public void onSoundButton(){
        if (ViewController.onButton.isSelected()) {
            mediaPlayer.pause();
            ViewController.onButton.setText("OFF");
        } else {
            ViewController.onButton.setText("ON");
        }
    }

    /** This method plays the background music. */
    public void playMusic(){
        if (!running){
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        }
    }

    /** When the ship jumping play the jump music. */
    public void playJumpMusic(){
        if(!running){
            jumpPlayer.stop();
        } else if (running){
            jumpPlayer.seek(jumpPlayer.getStartTime());
            jumpPlayer.play();
        }
    }
}
