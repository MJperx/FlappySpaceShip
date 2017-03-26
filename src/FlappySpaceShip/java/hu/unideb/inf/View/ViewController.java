package hu.unideb.inf.View;

import hu.unideb.inf.Core.Ship;
import hu.unideb.inf.Dao.HighScoreToXML;
import hu.unideb.inf.Dao.HighScore;
import hu.unideb.inf.Dao.HighScoreXMLObj;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

import static hu.unideb.inf.Core.Main.*;

public class ViewController {

    public Label descLabel = new Label("Flappy spaceship");
    public static Label scoreLabel = new Label("Score: " + score);
    public Label failLabel = new Label("FAIL");
    public Label newGameLabel = new Label("New Game");
    public Label highScoreLabel = new Label("HighScore");
    public Label exitLabel = new Label("Exit");
    public Label optionsLabel = new Label("Options");
    public Label backLabel = new Label("< Back");
    public Label soundText = new Label("Sound:");
    public Label leadBoardLabel = new Label("Add to leadboard");
    public Label doneLabel = new Label("Done");
    public Label playerNameLabel = new Label("Name:");
    public Label resumeLabel = new Label("Resume");
    public RadioButton onButton = new RadioButton("ON");
    public TextField playerName = new TextField();

    public static String player;

    private HighScore highScore = new HighScore();
    private HighScoreToXML highScoreToXML = new HighScoreToXML();

    public ViewController() {
    }

    public void init() {
        playerNameLabel.setStyle("-fx-translate-x: 310; -fx-translate-y: 405; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        playerNameLabel.setVisible(false);

        playerName.setBackground(Background.EMPTY);
        playerName.setStyle("-fx-translate-x: 410; -fx-translate-y: 400; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        playerName.setVisible(false);

        doneLabel.setStyle("-fx-translate-x: 340; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        doneLabel.setVisible(false);

        leadBoardLabel.setStyle("-fx-translate-x: 200; -fx-translate-y: 100; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        leadBoardLabel.setVisible(false);

        soundText.setStyle("-fx-translate-x: 310; -fx-translate-y: 405; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        soundText.setVisible(false);

        onButton.getStyleClass().remove("radio-button");
        onButton.getStyleClass().add("toggle-button");
        onButton.setBackground(Background.EMPTY);
        onButton.setStyle("-fx-translate-x: 430; -fx-translate-y: 400; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        onButton.setVisible(false);

        scoreLabel.setStyle("-fx-translate-x: 600; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");

        descLabel.setStyle("-fx-translate-x: 80; -fx-translate-y: 250; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 40px;");
        descLabel.setVisible(false);

        failLabel.setStyle("-fx-translate-x: 300; -fx-translate-y: 300; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 60px;");
        failLabel.setVisible(false);

        resumeLabel.setStyle("-fx-translate-x: 345; -fx-translate-y: 350; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        resumeLabel.setVisible(false);

        newGameLabel.setStyle("-fx-translate-x: 330; -fx-translate-y: 400; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        newGameLabel.setVisible(false);

        highScoreLabel.setStyle("-fx-translate-x: 320; -fx-translate-y: 450; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        highScoreLabel.setVisible(false);

        optionsLabel.setStyle("-fx-translate-x: 340; -fx-translate-y: 500; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        optionsLabel.setVisible(false);

        exitLabel.setStyle("-fx-translate-x: 370; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        exitLabel.setVisible(false);

        backLabel.setStyle("-fx-translate-x: 340; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        backLabel.setVisible(false);
    }

    private void hideLabels() {
        descLabel.setVisible(false);
        failLabel.setVisible(false);
        newGameLabel.setVisible(false);
        highScoreLabel.setVisible(false);
        optionsLabel.setVisible(false);
        exitLabel.setVisible(false);
        leadBoardLabel.setVisible(false);
        doneLabel.setVisible(false);
        playerName.setVisible(false);
        playerNameLabel.setVisible(false);
        resumeLabel.setVisible(false);
    }

    public void newGame(Ship ship) {
        if (!backLabel.isVisible())
            newGameLabel.setVisible(true);

        newGameLabel.setOnMouseEntered(MouseEvent -> {
            newGameLabel.setScaleX(1.5);
            newGameLabel.setScaleY(1.5);
        });

        newGameLabel.setOnMouseExited(MouseEvent -> {
            newGameLabel.setScaleX(1);
            newGameLabel.setScaleY(1);
        });

        newGameLabel.setOnMouseClicked(MouseEvent -> {
            if (!failGame) {
                running = true;
                score = 0;
                hideLabels();
            } else if (failGame) {
                running = true;
                ship.shipNull();
                score = 0;
                failGameCount++;
                hideLabels();
            }
        });
    }

    public void highScore() {
        if (!backLabel.isVisible())
            highScoreLabel.setVisible(true);

        highScoreLabel.setOnMouseEntered(MouseEvent -> {
            highScoreLabel.setScaleX(1.5);
            highScoreLabel.setScaleY(1.5);
        });

        highScoreLabel.setOnMouseExited(MouseEvent -> {
            highScoreLabel.setScaleX(1);
            highScoreLabel.setScaleY(1);
        });

        highScoreLabel.setOnMouseClicked(MouseEvent -> {
            HighScoreXMLObj.readToObject();
            backLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(false);
            optionsLabel.setVisible(false);
            exitLabel.setVisible(false);
            leadBoardLabel.setVisible(false);
            doneLabel.setVisible(false);
            playerName.setVisible(false);
            playerNameLabel.setVisible(false);
            resumeLabel.setVisible(false);
            backMenu();
        });
    }

    public void optionsMenu() {
        if (!backLabel.isVisible())
            optionsLabel.setVisible(true);

        optionsLabel.setOnMouseEntered(MouseEvent -> {
            optionsLabel.setScaleX(1.5);
            optionsLabel.setScaleY(1.5);
        });

        optionsLabel.setOnMouseExited(MouseEvent -> {
            optionsLabel.setScaleX(1);
            optionsLabel.setScaleY(1);
        });

        optionsLabel.setOnMouseClicked(MouseEvent -> {
            isOptions = true;
            optionsLabel.setFont(Font.font("Press Start 2P", 40));
            optionsLabel.setTranslateX(270);
            optionsLabel.setTranslateY(300);

            backLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(false);
            leadBoardLabel.setVisible(false);
            exitLabel.setVisible(false);
            soundText.setVisible(true);
            onButton.setVisible(true);
            doneLabel.setVisible(false);
            playerName.setVisible(false);
            playerNameLabel.setVisible(false);
            resumeLabel.setVisible(false);
            backMenu();
        });
    }

    public void exit() {
        if (!backLabel.isVisible())
            exitLabel.setVisible(true);

        exitLabel.setOnMouseEntered(MouseEvent -> {
            exitLabel.setScaleX(1.5);
            exitLabel.setScaleY(1.5);
        });

        exitLabel.setOnMouseExited(MouseEvent -> {
            exitLabel.setScaleX(1);
            exitLabel.setScaleY(1);
        });

        exitLabel.setOnMouseClicked(MouseEvent ->
                System.exit(0)
        );
    }

    public void backMenu() {
        backLabel.setOnMouseEntered(MouseEvent -> {
            backLabel.setScaleY(1.5);
            backLabel.setScaleX(1.5);
        });

        backLabel.setOnMouseExited(MouseEvent -> {
            backLabel.setScaleX(1);
            backLabel.setScaleY(1);
        });

        backLabel.setOnMouseClicked(MouseEvent -> {
            isOptions = false;
            if (!running && !failGame) {
                descLabel.setVisible(true);
            } else {
                failLabel.setVisible(true);
            }

            if (!isOptions) {
                optionsLabel.setFont(Font.font("Press Start 2P", 20));
                optionsLabel.setTranslateX(340);
                optionsLabel.setTranslateY(500);
            }

            if (score > 0)
                leadBoardLabel.setVisible(true);

            newGameLabel.setVisible(true);
            highScoreLabel.setVisible(true);
            optionsLabel.setVisible(true);
            exitLabel.setVisible(true);

            backLabel.setVisible(false);
            onButton.setVisible(false);
            soundText.setVisible(false);
            doneLabel.setVisible(false);
            playerName.setVisible(false);
            playerNameLabel.setVisible(false);
            resumeLabel.setVisible(false);
        });
    }

    public void addToLeadBoardMenu() {
        if (score >= 1)
            leadBoardLabel.setVisible(true);

        leadBoardLabel.setOnMouseEntered(MouseEvent -> {
            leadBoardLabel.setScaleY(1.5);
            leadBoardLabel.setScaleX(1.5);
        });

        leadBoardLabel.setOnMouseExited(MouseEvent -> {
            leadBoardLabel.setScaleX(1);
            leadBoardLabel.setScaleY(1);
        });

        leadBoardLabel.setOnMouseClicked(MouseEvent -> {
            backLabel.setVisible(true);
            playerName.setVisible(true);
            playerNameLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(false);
            optionsLabel.setVisible(false);
            exitLabel.setVisible(false);
            leadBoardLabel.setVisible(false);
            resumeLabel.setVisible(false);
            backMenu();

            playerName.setOnKeyPressed((event) -> {
                if(event.getCode() == KeyCode.ENTER) {
                    System.out.println(playerName.getText() + " " + score);
                    player = playerName.getText();
                    createXML();
                    playerName.setText("");
                    newGameLabel.setVisible(true);
                    highScoreLabel.setVisible(true);
                    optionsLabel.setVisible(true);
                    exitLabel.setVisible(true);
                    backLabel.setVisible(false);
                    playerName.setVisible(false);
                    playerNameLabel.setVisible(false);
                    backLabel.setVisible(false);
                    leadBoardLabel.setVisible(false);
                }
            });
        });
    }

    public void resumeMenu() {
        if (!backLabel.isVisible())
            resumeLabel.setVisible(true);

        resumeLabel.setOnMouseEntered(MouseEvent -> {
            resumeLabel.setScaleX(1.5);
            resumeLabel.setScaleY(1.5);
        });

        resumeLabel.setOnMouseExited(MouseEvent -> {
            resumeLabel.setScaleX(1);
            resumeLabel.setScaleY(1);
        });

        resumeLabel.setOnMouseClicked(MouseEvent -> {
            running = true;
            hideLabels();
        });
    }

    private void createXML(){
        highScore.setName(player);
        highScore.setScore(score);
        highScoreToXML.jaxbObjectToXML(highScore);
    }
}