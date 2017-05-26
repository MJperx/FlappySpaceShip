package hu.unideb.inf.Core;

import hu.unideb.inf.Dao.HighScore;
import hu.unideb.inf.Dao.HighScoreDAOImp;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static java.lang.Math.random;

/**
 * This class is the Main class of the application.
 * @author MJ
 */
public class Main extends Application {

    /** {@link Logger} for logging.*/
    private static Logger logger = LoggerFactory.getLogger( Main.class );

    private Ship ship = new Ship();
    private ViewController viewController = new ViewController();
    private MediaController mediaController = new MediaController();
    private Effects effects = new Effects();

    /** Width of the window. */
    public static final int winWidth = 800;

    /** Height of the window. */
    public static final int winHeight= 600;

    /** Score of the player. */
    public static int score = 0;

    private static Pane appRoot = new Pane();
    private static Pane gameRoot = new Pane();

    /** List of the pipes. */
    public static final ArrayList<Wall> walls = new ArrayList<>();

    /** List of the pipes. */
    public static final ArrayList<Wall> walls2 = new ArrayList<>();

    /** The game is running or not. */
    public static boolean running = false;

    /** This ship is hit a pipe or not. */
    public static boolean failGame = false;

    /** Is it the options menu? */
    public static boolean isOptions = false;

    /** Is it the highscore menu? */
    public static boolean isHighScore = false;

    /** If true can play effects. */
    public static boolean effectPlaying = false;

    private Image img_g = new Image(getClass().getResourceAsStream("spaceShip.png"));
    private Image img_r = new Image(getClass().getResourceAsStream("spaceShip_r.png"));

    /**
     * Add a highscore to the HighScores.xml.
     * @param player Name of the player.
     * @param score The reached score.
     */
    public static void initData(String player, int score) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        HighScore highScore = new HighScore(player, String.valueOf(score), formattedDateTime);
        HighScoreDAOImp higshScoreDAO = new HighScoreDAOImp();
        higshScoreDAO.addHighScore(highScore);
    }

    private Parent createContent(){
        gameRoot.setPrefSize(winWidth, winHeight);

        logger.debug("Creating walls...");
        for (int i = 0; i < 100; i++) {
            int enter = (int)(random()*100+100);
            int height = new Random().nextInt(winHeight-enter);
            Wall wall = new Wall(height, 1);
            wall.setTranslateX(i*350+winHeight);
            wall.setTranslateY(0);
            walls.add(wall);
            walls2.add(wall);

            Wall wall2 = new Wall(winHeight-enter-height, 0);
            wall2.setTranslateX(i*350+winHeight);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall,wall2);
        }

        logger.debug("Walls are created.");

        appRoot.setBackground(mediaController.bg);

        gameRoot.getChildren().add(ship);
        appRoot.getChildren().addAll(gameRoot, viewController.descLabel, viewController.scoreLabel, viewController.failLabel,
                                     viewController.newGameLabel, viewController.highScoreLabel, viewController.optionsLabel,
                                     viewController.exitLabel, viewController.backLabel, viewController.onButton, viewController.soundText,
                                     viewController.leadBoardLabel, viewController.doneLabel, viewController.playerName,
                                     viewController.playerNameLabel, viewController.resumeLabel, viewController.tableView
                                     );

        return appRoot;
    }

    private void update() {

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
                ship.rect.setEffect(Effects.randomColor());
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
                effects.scaleEffect(viewController.failLabel);
                viewController.failLabel.setVisible(true);
                viewController.failLabel.setTextFill(Effects.randomTextColor());
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
        mediaController.playMusic();
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
                logger.debug("Game paused.");
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

    /**
     * Main method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        logger.info("Application started!");
        launch(args);
    }
}
