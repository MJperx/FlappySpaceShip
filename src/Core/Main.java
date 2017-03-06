package Core;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    Ship ship = new Ship();

    public static int winWidth = 800;
    public static int winHeight= 600;

    public static int score = 0;

    private static Pane appRoot = new Pane();
    private static Pane gameRoot = new Pane();

    private static MediaPlayer mediaPlayer;
    private static MediaPlayer jumpPlayer;

    private static final String backgroudURL = "res/bg2.png";
    private static final String mainMusicURL = "res/mainMusic.wav";
    private static final String jumpSoundURL = "res/jumpSound.wav";

    public static ArrayList<Wall> walls = new ArrayList<>();

    //region Label declaration
    private Label descLabel = new Label("Flappy spaceship");
    private Label scoreLabel = new Label("Score: " + score);
    private Label failLabel = new Label("FAIL");
    private Label newGameLabel = new Label("New Game");
    private Label highScoreLabel = new Label("HighScore");
    private Label exitLabel = new Label("Exit");

    private Label optionsLabel = new Label("Options");
    private Label backLabel = new Label("< Back");
    //endregion

    public static boolean running = false;
    public static boolean failGame = false;

    private Parent createContent(){
        gameRoot.setPrefSize(winWidth, winHeight);

        //region Label init
        descLabel.setFont(Font.font("Press Start 2P", 40));
        scoreLabel.setFont(Font.font("Press Start 2P", 20));
        failLabel.setFont(Font.font("Press Start 2P", 60));
        newGameLabel.setFont(Font.font("Press Start 2P", 20));
        highScoreLabel.setFont(Font.font("Press Start 2P", 20));
        exitLabel.setFont(Font.font("Press Start 2P", 20));
        optionsLabel.setFont(Font.font("Press Start 2P", 20));
        backLabel.setFont(Font.font("Press Start 2P", 20));

        scoreLabel.setTranslateX(600);
        scoreLabel.setTextFill(Color.WHITE);

        descLabel.setTranslateX(80);
        descLabel.setTranslateY(250);
        descLabel.setTextFill(Color.WHITE);
        descLabel.setVisible(false);
        
        failLabel.setTranslateX(300);
        failLabel.setTranslateY(300);
        failLabel.setTextFill(Color.WHITE);
        failLabel.setVisible(false);

        newGameLabel.setTranslateX(330);
        newGameLabel.setTranslateY(400);
        newGameLabel.setTextFill(Color.WHITE);
        newGameLabel.setVisible(false);

        highScoreLabel.setTranslateX(320);
        highScoreLabel.setTranslateY(450);
        highScoreLabel.setTextFill(Color.WHITE);
        highScoreLabel.setVisible(false);

        optionsLabel.setTranslateX(340);
        optionsLabel.setTranslateY(500);
        optionsLabel.setTextFill(Color.WHITE);
        optionsLabel.setVisible(false);

        exitLabel.setTranslateX(370);
        exitLabel.setTranslateY(550);
        exitLabel.setTextFill(Color.WHITE);
        exitLabel.setVisible(false);

        backLabel.setTranslateX(340);
        backLabel.setTranslateY(550);
        backLabel.setTextFill(Color.WHITE);
        backLabel.setVisible(false);
        //endregion
        //region Pipe init
        for (int i = 0; i < 100; i++) {
            int enter = (int)(Math.random()*100+100);
            int height = new Random().nextInt(winHeight-enter);
            Wall wall = new Wall(height, 1);
            wall.setTranslateX(i*350+winHeight);
            wall.setTranslateY(0);
            walls.add(wall);

            Wall wall2 = new Wall(winHeight-enter-height, 0);
            wall2.setTranslateX(i*350+winHeight);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall,wall2);
        }
        //endregion
        //region Sound's
        Media sound = new Media(getClass().getClassLoader().getResource(mainMusicURL).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);

        Media juMedia = new Media(getClass().getClassLoader().getResource(jumpSoundURL).toExternalForm());
        jumpPlayer = new MediaPlayer(juMedia);
        //endregion
        //region Background
        Image img = new Image(getClass().getClassLoader().getResource(backgroudURL).toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background bg = new Background(bgImg);
        appRoot.setBackground(bg);
        //endregion
        //region Components
        gameRoot.getChildren().add(ship);
        appRoot.getChildren().addAll(gameRoot, descLabel, scoreLabel, failLabel, newGameLabel, highScoreLabel, optionsLabel, exitLabel, backLabel);
        //endregion

        return appRoot;
    }

    private void playMusic(){
        if (!running){
            mediaPlayer.play();
        } else if (running) {
            mediaPlayer.setVolume(50);
        }
    }

    private void playJumpMusic(){
        if(!running){
            jumpPlayer.stop();
        } else if (running){
            jumpPlayer.seek(jumpPlayer.getStartTime());
            jumpPlayer.play();
        }
    }

    private void newGame() {
        if (!backLabel.isVisible())
    	    newGameLabel.setVisible(true);

        //region newGameLabel scale
        newGameLabel.setOnMouseEntered(MouseEvent -> {
                newGameLabel.setScaleX(1.5);
                newGameLabel.setScaleY(1.5);
        });

        newGameLabel.setOnMouseExited(MouseEvent -> {
                newGameLabel.setScaleX(1);
                newGameLabel.setScaleY(1);
        });
        //endregion
        //region newGameLabel onMouseClicked
        newGameLabel.setOnMouseClicked(MouseEvent -> {
            if (!failGame) {
                running = true;

                score = 0;

                descLabel.setVisible(false);
                failLabel.setVisible(false);
                newGameLabel.setVisible(false);
                highScoreLabel.setVisible(false);
                optionsLabel.setVisible(false);
                exitLabel.setVisible(false);
                backLabel.setVisible(false);

            } else if (failGame) {
                running = true;

                ship.velocity.add(0,0);
                ship.setTranslateX(100);
                ship.setTranslateY(300);

                score = 0;

                failLabel.setVisible(false);
                newGameLabel.setVisible(false);
                highScoreLabel.setVisible(false);
                optionsLabel.setVisible(false);
                exitLabel.setVisible(false);
                backLabel.setVisible(false);
            }
        });
        //endregion
    }
    
    private void highScore() {
        if (!backLabel.isVisible())
    	    highScoreLabel.setVisible(true);

        //region highScoreLabel scale
        highScoreLabel.setOnMouseEntered(MouseEvent -> {
                highScoreLabel.setScaleX(1.5);
                highScoreLabel.setScaleY(1.5);
        });

        highScoreLabel.setOnMouseExited(MouseEvent -> {
                highScoreLabel.setScaleX(1);
                highScoreLabel.setScaleY(1);
        });
        //endregione

        highScoreLabel.setOnMouseClicked(MouseEvent -> {
            backLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(false);
            optionsLabel.setVisible(false);
            exitLabel.setVisible(false);
            backMenu();
        });
    }

    private void optionsMenu() {
        if (!backLabel.isVisible())
            optionsLabel.setVisible(true);

        //region optionsLabel scale
        optionsLabel.setOnMouseEntered(MouseEvent -> {
            optionsLabel.setScaleX(1.5);
            optionsLabel.setScaleY(1.5);
        });

        optionsLabel.setOnMouseExited(MouseEvent -> {
            optionsLabel.setScaleX(1);
            optionsLabel.setScaleY(1);
        });
        //endregion

        optionsLabel.setOnMouseClicked(MouseEvent -> {
                backLabel.setVisible(true);
                failLabel.setVisible(false);
                newGameLabel.setVisible(false);
                highScoreLabel.setVisible(false);
                optionsLabel.setVisible(false);
                exitLabel.setVisible(false);
                backMenu();
        });
    }

    private void exit() {
        if (!backLabel.isVisible())
            exitLabel.setVisible(true);

        //region exitLabel scale
        exitLabel.setOnMouseEntered(MouseEvent -> {
        	exitLabel.setScaleX(1.5);
            exitLabel.setScaleY(1.5);
        });

        exitLabel.setOnMouseExited(MouseEvent -> {
            exitLabel.setScaleX(1);
            exitLabel.setScaleY(1);
        });
        //endregion

        exitLabel.setOnMouseClicked(MouseEvent ->
            System.exit(0)
        );
    }

    private void backMenu(){
        //region backLabel scale
        backLabel.setOnMouseEntered(MouseEvent -> {
            backLabel.setScaleY(1.5);
            backLabel.setScaleX(1.5);
        });

        backLabel.setOnMouseExited(MouseEvent -> {
            backLabel.setScaleX(1);
            backLabel.setScaleY(1);
        });
        //endregion

        backLabel.setOnMouseClicked(MouseEvent -> {
            if (!running && !failGame) {
                descLabel.setVisible(true);
            } else {
                failLabel.setVisible(true);
            }
            newGameLabel.setVisible(true);
            highScoreLabel.setVisible(true);
            optionsLabel.setVisible(true);
            exitLabel.setVisible(true);
            backLabel.setVisible(false);
        });
    }

    private void update() {
        playMusic();
        if (running) {
            scoreLabel.setVisible(true);
            if (ship.velocity.getY() < 5) {
                ship.velocity = ship.velocity.add(0, 1);
            }

            ship.moveX((int) ship.velocity.getX());
            ship.moveY((int) ship.velocity.getY());
            scoreLabel.setText("Score: " + score);

            ship.translateXProperty().addListener((ods, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 200) {
                    gameRoot.setLayoutX(-(offset - 200));
                }
            });
        } else if ( !running && !failGame) {
            if (backLabel.isVisible()){
        	    descLabel.setVisible(false);
            } else {
                descLabel.setVisible(true);
            }
            scoreLabel.setVisible(false);
        	newGame();
        	highScore();
            optionsMenu();
            exit();
        } else if (!running && failGame){
            if (!backLabel.isVisible())
                failLabel.setVisible(true);
        	newGame();
        	highScore();
            optionsMenu();
            exit();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnMouseClicked(event -> {
            playJumpMusic();
            ship.jump();
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    update();
            }
        };

        timer.start();
    }

    public static void main(String[] args) { launch(args); }
}
