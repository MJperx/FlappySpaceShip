package hu.unideb.inf.View;

import hu.unideb.inf.Core.Main;
import hu.unideb.inf.Core.Ship;
import hu.unideb.inf.Dao.HighScore;
import hu.unideb.inf.Dao.HighScoreDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

import static hu.unideb.inf.Core.Main.*;

/**
 * ViewController class implement the graphical elements.
 */
public class ViewController {

    private HighScoreDAOImp highScoreDAOImp = new HighScoreDAOImp();
    private ArrayList<HighScore> highScoreArrayList;
    private ObservableList<HighScore> lista;

    /** "Description" label. */
    public Label descLabel = new Label("Flappy spaceship");

    /** "Score" label. */
    public static Label scoreLabel = new Label("Score: " + score);

    /** "Fail" label. */
    public Label failLabel = new Label("FAIL");

    /** "New Game" label. */
    public Label newGameLabel = new Label("New Game");

    /** "HighScore" label. */
    public Label highScoreLabel = new Label("HighScore");

    /** "Exit" label. */
    public Label exitLabel = new Label("Exit");

    /** "Options" label. */
    public Label optionsLabel = new Label("Options");

    /** "Back" label. */
    public Label backLabel = new Label("< Back");

    /** "Sound" label. */
    public Label soundText = new Label("Sound:");

    /** "Leadboard" label. */
    public Label leadBoardLabel = new Label("Add to leadboard");

    /** "Done" label. */
    public Label doneLabel = new Label("Done");

    /** "Name" label. */
    public Label playerNameLabel = new Label("Name:");

    /** "Resume" label. */
    public Label resumeLabel = new Label("Resume");

    /** Sound on/off button. */
    public static RadioButton onButton = new RadioButton("ON");

    /** Name field. */
    public TextField playerName = new TextField();

    /** Highscore table. */
    public TableView<HighScore> listView = new TableView<>();

    /** Name of the player. */
    private static String player;

    private HighScore highScore = new HighScore();

    /** Constructor. */
    public ViewController() {
        init();
        initTable();
    }

    /**
     * Initialize the properties of the JavaFX elements.
     */
    private void init() {
        playerNameLabel.setStyle("-fx-translate-x: 310; -fx-translate-y: 405; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        playerNameLabel.setVisible(false);

        playerName.setBackground(Background.EMPTY);
        playerName.setStyle("-fx-translate-x: 410; -fx-translate-y: 400; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        playerName.setVisible(false);

        doneLabel.setStyle("-fx-translate-x: 340; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        doneLabel.setVisible(false);

        leadBoardLabel.setStyle("-fx-translate-x: 250; -fx-translate-y: 350; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
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

        failLabel.setStyle("-fx-translate-x: 300; -fx-translate-y: 200; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 60px;");
        failLabel.setVisible(false);

        resumeLabel.setStyle("-fx-translate-x: 345; -fx-translate-y: 350; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        resumeLabel.setVisible(false);

        newGameLabel.setStyle("-fx-translate-x: 330; -fx-translate-y: 400; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        newGameLabel.setVisible(false);

        highScoreLabel.setStyle("-fx-translate-x: 320; -fx-translate-y: 450; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        highScoreLabel.setVisible(false);

        exitLabel.setStyle("-fx-translate-x: 370; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        exitLabel.setVisible(false);

        backLabel.setStyle("-fx-translate-x: 340; -fx-translate-y: 550; -fx-text-fill: white;  -fx-font-family: 'Press Start 2P'; -fx-font-size: 20px;");
        backLabel.setVisible(false);

        optionsLabel.setFont(Font.font("Press Start 2P", 20));
        optionsLabel.setTextFill(Color.WHITE);
        optionsLabel.setTranslateX(340);
        optionsLabel.setTranslateY(500);
        optionsLabel.setVisible(false);

        listView.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 20px; -fx-text-fill: white; " +
                          "-fx-background-color: transparent; -fx-base: transparent;"+
                          "-fx-table-header-border-color: transparent; -fx-border-color: transparent; " +
                          "-fx-table-cell-border-color: transparent; -fx-control-inner-background: transparent;" +
                          "-fx-translate-x: 240; -fx-translate-y: 300; -fx-pref-width: 450; -fx-pref-height: 262;");
        listView.setVisible(false);
        listView.setSelectionModel(null);
    }

    /**
     * Initialize the table and load content.
     */
    private void initTable() {
        TableColumn name = new TableColumn("");
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn score = new TableColumn("");
        score.setMinWidth(50);
        score.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        listView.getColumns().addAll(name, score);


        highScoreArrayList = highScoreDAOImp.getAllHighScores().getHighScore();
        lista = FXCollections.observableArrayList();
        for (HighScore highScore : highScoreArrayList) {
            lista.add(highScore);
        }

        listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        listView.setItems(lista);
    }

    /**
     * Refreshing the content of the table.
     */
    private void refreshTable(){
        highScoreArrayList = highScoreDAOImp.getAllHighScores().getHighScore();
        lista = FXCollections.observableArrayList();
        for (HighScore highScore : highScoreArrayList) {
            lista.add(highScore);
        }
        listView.setItems(lista);
        listView.refresh();
    }

    /**
     * Hide the specified labels.
     */
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
        listView.setVisible(false);
    }

    /**
     * Play scale effect to the newGameLabel or begins a new game.
     *
     * @param ship The spaceship.
     */
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
                hideLabels();
            }
        });
    }

    /**
     * Play scale effect to the highScoreLabel or refreshing the content.
     */
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
            isHighScore = true;

            refreshTable();

            highScoreLabel.setTranslateX(320);
            highScoreLabel.setTranslateY(250);

            listView.setVisible(true);
            backLabel.setVisible(true);
            failLabel.setVisible(false);
            newGameLabel.setVisible(false);
            highScoreLabel.setVisible(true);
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

    /**
     * Play scale effect to the optionsLabel or opening the options menu.
     */
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
            listView.setVisible(false);
            backMenu();
        });
    }

    /**
     * Play scale effect to the exitLabel or close the game.
     */
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

    /**
     * Play scale effect to the backLabel or back to the previous menu.
     */
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
            isHighScore = false;
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

            /*if (!isHighScore) {
                leadBoardLabel.setVisible(false);
            }*/

            if (score > 0 && isHighScore) {
                leadBoardLabel.setVisible(true);
            } else if (score > 0 && !isHighScore) {
                leadBoardLabel.setVisible(false);
            }

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
            listView.setVisible(false);
            highScoreLabel.setTranslateX(320);
            highScoreLabel.setTranslateY(450);
        });
    }

    /**
     * Play scale effect to the leadBoardLabel or add new highscore the the HighScores.xml.
     */
    public void addToLeadBoardMenu() {
        if (score >= 1 && !isHighScore && !isOptions)
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
            listView.setVisible(false);
            backMenu();
            playerName.setOnKeyPressed((event) -> {
                if(event.getCode() == KeyCode.ENTER) {
                    System.out.println(playerName.getText() + " " + score);
                    player = playerName.getText();
                    Main.initData(player,Main.score);
                    playerName.setText("");
                    newGameLabel.setVisible(true);
                    highScoreLabel.setVisible(true);
                    optionsLabel.setVisible(true);
                    exitLabel.setVisible(true);
                    backLabel.setVisible(false);
                    playerName.setVisible(false);
                    playerNameLabel.setVisible(false);
                    backLabel.setVisible(false);
                    listView.setVisible(false);
                    leadBoardLabel.setVisible(false);
                }
            });
        });
    }

    /**
     * Play scale effect to the highScoreLabel or pausing the game.
     */
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
}