package hu.unideb.inf.View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static hu.unideb.inf.Core.Main.running;

public class MediaController {

    private static final String backgroudURL = "bg2.png";
    private static final String mainMusicURL = "mainMusic.wav";
    private static final String jumpSoundURL = "jumpSound.wav";

    private MediaPlayer mediaPlayer;
    private MediaPlayer jumpPlayer;
    public Background bg;

    public MediaController(){
        init();
    }

    public void init(){
        Media sound = new Media(getClass().getResource(mainMusicURL).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);

        Media juMedia = new Media(getClass().getResource(jumpSoundURL).toExternalForm());
        jumpPlayer = new MediaPlayer(juMedia);

        Image img = new Image(getClass().getResourceAsStream(backgroudURL));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bg = new Background(bgImg);
    }

    public void onSoundButton(){
        if (ViewController.onButton.isSelected()) {
            mediaPlayer.pause();
            ViewController.onButton.setText("OFF");
        } else {
            ViewController.onButton.setText("ON");
        }
    }

    public void playMusic(){
        if (!running){
            mediaPlayer.seek(mediaPlayer.getStartTime());
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
