package hu.unideb.inf.View;

import hu.unideb.inf.Core.Main;
import hu.unideb.inf.Core.Ship;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static hu.unideb.inf.Core.Main.*;

public class ViewController {

    public Label descLabel = new Label("Flappy spaceship");
    public Label scoreLabel = new Label("Score: " + score);
    public Label failLabel = new Label("FAIL");
    public Label newGameLabel = new Label("New Game");
    public Label highScoreLabel = new Label("HighScore");
    public Label exitLabel = new Label("Exit");
    public Label optionsLabel = new Label("Options");
    public Label backLabel = new Label("< Back");
    public Label soundText = new Label("Sound:");
    public RadioButton onButton = new RadioButton("ON");

    public ViewController(){}

    public void init(){
        descLabel.setFont(Font.font("Press Start 2P", 40));
        scoreLabel.setFont(Font.font("Press Start 2P", 20));
        failLabel.setFont(Font.font("Press Start 2P", 60));
        newGameLabel.setFont(Font.font("Press Start 2P", 20));
        highScoreLabel.setFont(Font.font("Press Start 2P", 20));
        optionsLabel.setFont(Font.font("Press Start 2P", 20));
        exitLabel.setFont(Font.font("Press Start 2P", 20));
        backLabel.setFont(Font.font("Press Start 2P", 20));
        onButton.setFont(Font.font("Press Start 2P", 20));
        soundText.setFont(Font.font("Press Start 2P", 20));

        soundText.setTranslateX(310);
        soundText.setTranslateY(405);
        soundText.setTextFill(Color.WHITE);
        soundText.setVisible(false);

        onButton.setTranslateX(430);
        onButton.setTranslateY(400);
        onButton.getStyleClass().remove("radio-button");
        onButton.getStyleClass().add("toggle-button");
        onButton.setBackground(Background.EMPTY);
        onButton.setTextFill(Color.WHITE);
        onButton.setVisible(false);

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

                descLabel.setVisible(false);
                failLabel.setVisible(false);
                newGameLabel.setVisible(false);
                highScoreLabel.setVisible(false);
                optionsLabel.setVisible(false);
                exitLabel.setVisible(false);
                backLabel.setVisible(false);

            } else if (failGame) {
                running = true;

                ship.shipNull();

                score = 0;

                failLabel.setVisible(false);
                newGameLabel.setVisible(false);
                highScoreLabel.setVisible(false);
                optionsLabel.setVisible(false);
                exitLabel.setVisible(false);
                backLabel.setVisible(false);
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
            backLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(false);
            optionsLabel.setVisible(false);
            exitLabel.setVisible(false);
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
            exitLabel.setVisible(false);
            soundText.setVisible(true);
            onButton.setVisible(true);
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

    public void backMenu(){
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

            if (!isOptions){
                optionsLabel.setFont(Font.font("Press Start 2P", 20));
                optionsLabel.setTranslateX(340);
                optionsLabel.setTranslateY(500);
            }
            newGameLabel.setVisible(true);
            highScoreLabel.setVisible(true);
            optionsLabel.setVisible(true);
            exitLabel.setVisible(true);
            backLabel.setVisible(false);
            onButton.setVisible(false);
            soundText.setVisible(false);
        });
    }
}
