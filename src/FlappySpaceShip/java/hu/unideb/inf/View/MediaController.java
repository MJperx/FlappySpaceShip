package hu.unideb.inf.View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static hu.unideb.inf.Core.Main.running;

public class MediaController {

    private static final String backgroudURL = "/images/bg2.png";
    private static final String mainMusicURL = "/music/mainMusic.wav";
    private static final String jumpSoundURL = "/music/jumpSound.wav";

    private MediaPlayer mediaPlayer;
    private MediaPlayer jumpPlayer;
    public Background bg;

    public MediaController(){}

    public void init(){
        Media sound = new Media(getClass().getResource(mainMusicURL).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);

        Media juMedia = new Media(getClass().getResource(jumpSoundURL).toExternalForm());
        jumpPlayer = new MediaPlayer(juMedia);

        Image img = new Image(getClass().getResource(backgroudURL).toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bg = new Background(bgImg);
    }

    public void onSoundButton(){
        ViewController viewController = new ViewController();
        if (viewController.onButton.isSelected()) {
            mediaPlayer.pause();
            viewController.onButton.setText("OFF");
        } else {
            viewController.onButton.setText("ON");
        }
    }

    public void playMusic(){
        if (!running){
            mediaPlayer.play();
        } else if (running) {
            mediaPlayer.setVolume(50);
        }
    }

    public void playJumpMusic(){
        if(!running){
            jumpPlayer.stop();
        } else if (running){
            jumpPlayer.seek(jumpPlayer.getStartTime());
            jumpPlayer.play();
        }
    }
}
