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

    public String getDate() {
        return date.get();
    }

    @XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }

    public String getScore() {
        return score.get();
    }

    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    public String getName() {
        return name.get();
    }

    @XmlElement
    public void setName(String name) {
        this.name.set(name);
    }

    public HighScore(String name, String score, String date) {
        super();
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.name.set(name);
        this.score.set(score);
        this.date.set(date);
    }

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