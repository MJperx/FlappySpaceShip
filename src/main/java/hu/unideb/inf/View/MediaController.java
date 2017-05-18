package hu.unideb.inf.View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



import static hu.unideb.inf.Core.Main.running;

/**
 * MediaController class. Initialize the used medias.
 */
public class MediaController {

    //private static final Logger LOG = LoggerFactory.getLogger(MediaController.class);


    private static final String backgroundURL = "bg2.png";
    private static final String mainMusicURL = "mainMusic.wav";
    private static final String jumpSoundURL = "jumpSound.wav";

    private MediaPlayer mediaPlayer;
    private MediaPlayer jumpPlayer;

    /** The background of the application. */
    public Background bg;

    /** Constructor. */
    public MediaController(){
        init();
    }

    /**
     * Initialize the medias.
     */
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

    /**
     * Plays or pause the background music.
     */
    public void onSoundButton(){
        if (ViewController.onButton.isSelected()) {
            mediaPlayer.pause();
            ViewController.onButton.setText("OFF");
        } else {
            ViewController.onButton.setText("ON");
        }
    }

    /**
     * This method plays the background music.
     */
    public void playMusic(){
        if (!running){
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        }
    }

    /**
     * When the ship jumping play the jump music.
     */
    public void playJumpMusic(){
        if(!running){
            jumpPlayer.stop();
        } else if (running){
            jumpPlayer.seek(jumpPlayer.getStartTime());
            jumpPlayer.play();
        }
    }
}
