package hu.unideb.inf.Dao;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class store data about winner of a match.
 *
 * @author MJ
 *
 */
@XmlRootElement
public class HighScore {
    private SimpleStringProperty name;
    private SimpleStringProperty score;
    private SimpleStringProperty date;

    /**
     * Get the date.
     *
     * @return The date.
     */
    public String getDate() {
        return date.get();
    }
    /**
     * Sets the date.
     *
     * @param date The date.
     */
    @XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Gets the score.
     *
     * @return The score.
     */
    public String getScore() {
        return score.get();
    }

    /**
     * Sets the score.
     *
     * @param score Score.
     */
    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    /**
     * Gets the name of the player.
     *
     * @return The name.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name of the player.
     */
    @XmlElement
    public void setName(String name) {
        this.name.set(name);
    }


    /**
     * Constructor of the Highscore class.
     *
     * @param date Date.
     *
     * @param name Name of the player.
     *
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


    /**
     * Constructor of the Highscore class.
     */
    public HighScore(){
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
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