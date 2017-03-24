package hu.unideb.inf.Dao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "HighScore")
@XmlType(propOrder = {"name", "score"})
public class HighScore {
    private String name;
    private int score;
    private int id;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @XmlElement
    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
}