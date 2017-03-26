package hu.unideb.inf.Core;

import hu.unideb.inf.View.Effects;
import hu.unideb.inf.View.MediaController;
import hu.unideb.inf.View.ViewController;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    private Ship ship = new Ship();
    private ViewController viewController = new ViewController();
    private MediaController mediaController = new MediaController();
    private Effects effects = new Effects();

    public static int winWidth = 800;
    public static int winHeight= 600;

    public static int score = 0;

    private static Pane appRoot = new Pane();
    private static Pane gameRoot = new Pane();

    public static ArrayList<Wall> walls = new ArrayList<>();

    public static boolean running = false;
    public static boolean failGame = false;
    public static boolean isOptions = false;
    public static boolean isResume = false;
    public static boolean effectPlaying = true;


    public static int failGameCount = 0;

    Image img_g = new Image(getClass().getResource("/images/spaceShip.png").toExternalForm());
    Image img_r = new Image(getClass().getResource("/images/spaceShip_r.png").toExternalForm());

    private Parent createContent(){
        gameRoot.setPrefSize(winWidth, winHeight);

        viewController.init();
        mediaController.init();

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

        appRoot.setBackground(mediaController.bg);

        gameRoot.getChildren().add(ship);
        appRoot.getChildren().addAll(gameRoot, viewController.descLabel, viewController.scoreLabel, viewController.failLabel,
                                     viewController.newGameLabel, viewController.highScoreLabel, viewController.optionsLabel,
                                     viewController.exitLabel, viewController.backLabel, viewController.onButton, viewController.soundText,
                                     viewController.leadBoardLabel, viewController.doneLabel, viewController.playerName,
                                     viewController.playerNameLabel, viewController.resumeLabel
                                     );

        return appRoot;
    }

    private void update() {
        mediaController.playMusic();
        if (running) {
            viewController.scoreLabel.setVisible(true);
            if (ship.velocity.getY() < 5) {
                ship.velocity = ship.velocity.add(0, 1);
            }

            ship.moveX((int) ship.velocity.getX());
            ship.moveY((int) ship.velocity.getY());
            viewController.scoreLabel.setText("Score: " + score);

            ship.translateXProperty().addListener((ods, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 200) {
                    gameRoot.setLayoutX(-(offset - 200));
                }
            });

            if (score < 10) {
                ship.rect.setFill(new ImagePattern(img_g, 0, 0, 1, 1, true));
            } else if (score >= 10) {
                ship.rect.setFill(new ImagePattern(img_r, 0, 0, 1, 1, true));
            }

            if (score % 10 == 0 && score != 0) {
                effects.scaleEffect(ViewController.scoreLabel);
            }
            effectPlaying = true;

        } else if ( !running && !failGame) {
            if (viewController.backLabel.isVisible()){
        	    viewController.descLabel.setVisible(false);
            } else {
                viewController.descLabel.setVisible(true);
            }
            viewController.scoreLabel.setVisible(false);
            viewController.newGame(ship);
            viewController.highScore();
            viewController.optionsMenu();
            mediaController.onSoundButton();
            viewController.addToLeadBoardMenu();
            viewController.exit();
        } else if (!running && failGame){
            if (!viewController.backLabel.isVisible()) {
               // effects.scaleEffect(viewController.failLabel);
                viewController.failLabel.setVisible(true);
            }
            viewController.newGame(ship);
            viewController.highScore();
            viewController.optionsMenu();
            viewController.addToLeadBoardMenu();
            mediaController.onSoundButton();
            viewController.exit();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnMouseClicked(event -> {
            mediaController.playJumpMusic();
            ship.jump();
        });
        effectPlaying = true;
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE){
                ship.jump();
                mediaController.playJumpMusic();
                effectPlaying = false;
            }

            if (running && event.getCode() == KeyCode.ESCAPE){
                running = false;
                failGame = false;
                viewController.newGame(ship);
                viewController.highScore();
                viewController.optionsMenu();
                mediaController.onSoundButton();
                viewController.addToLeadBoardMenu();
                viewController.resumeMenu();
                viewController.descLabel.setVisible(true);
            }
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
