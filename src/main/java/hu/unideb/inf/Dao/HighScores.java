package hu.unideb.inf.Dao;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The {@code HighScores} class implement an {@link ArrayList}
 * which stores {@link HighScore} objects.
 * @author MJ
 */
@XmlRootElement(name = "highScores")
public class HighScores{
    private ArrayList<HighScore> highScores;
    private static final int MAXLENGTH = 5;

    /**
     * Creates a new instance of {@code HighScores}.
     */
    public HighScores() {
        this.highScores = new ArrayList<HighScore>();
    }

    /**
     * Set the highscore.
     * @param highScore List of highscores.
     */
    @XmlElement
    public void setHighScore(ArrayList<HighScore> highScore) {
        this.highScores = highScore;
    }

    /**
     * Get sorted highScores by score.
     * @return List of high scores.
     */
    public ArrayList<HighScore> getHighScore() {
        Collections.sort(highScores, (hs1, hs2) -> {
            return Integer.parseInt(hs2.getScore()) - Integer.parseInt(hs1.getScore());
        });

        if (highScores.size() > MAXLENGTH) {
            for (int i = 0; i < highScores.size(); i++) {
                highScores.subList(MAXLENGTH, highScores.size()).clear();
            }
        }

        return highScores;
    }

    /**
     * Add new highScore to highScoreList.
     * @param hs A highscore.
     */
    public void addHighScore(HighScore hs) { highScores.add(hs); }
}