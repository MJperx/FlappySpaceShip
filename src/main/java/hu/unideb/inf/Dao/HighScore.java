package hu.unideb.inf.Dao;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class store data about winner of a match.
 * @author MJ
 */
@XmlRootElement
public class HighScore {
    private SimpleStringProperty name;
    private SimpleStringProperty score;
    private SimpleStringProperty date;

    /**
     * Get the date of the high score.
     * @return The date of the high score.
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Sets the date to the high score.
     * @param date The specified date.
     */
    @XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Gets the score of the high score.
     * @return The score of the high score.
     */
    public String getScore() {
        return score.get();
    }

    /**
     * Sets the score to the high score.
     * @param score The specified score.
     */
    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the player.
     * @param name The name of the player.
     */
    @XmlElement
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Creates an empty instance of the {@code HighScore} class.
     * */
    public HighScore(){
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
    }

    /**
     * Creates an instance of the {@code HighScore} class and
     * sets the name, score and date.
     * @param date Actual Date.
     * @param name Name of the player.
     * @param score Score of the player.
     **/
    public HighScore(String name, String score, String date) {
        super();
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.name.set(name);
        this.score.set(score);
        this.date.set(date);
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "name=" + name +
                ", score=" + score +
                ", date=" + date +
                '}';
    }
}