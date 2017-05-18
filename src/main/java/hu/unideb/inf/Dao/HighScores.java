package hu.unideb.inf.Dao;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the root element of the HighScore.xml file.
 *
 * @author MJ
 *
 */
@XmlRootElement(name = "highScores")
public class HighScores{

    private ArrayList<HighScore> highScores;
    private static final int MAXLENGTH = 5;

    /**
     * Controctor of the HighScores class.
     *
     * @param highScores List of highscores.
     */
    public HighScores(ArrayList<HighScore> highScores) {
        super();
        this.highScores = highScores;
    }

    /**
     * Contructor of the HighhScores class.
     */
    public HighScores() {
        this.highScores = new ArrayList<HighScore>();
    }

    /**
     * Set the highscore.
     *
     * @param highScore List of highscores.
     */
    @XmlElement
    public void setHighScore(ArrayList<HighScore> highScore) {
        this.highScores = highScore;
    }

    /**
     * Get sorted highScores by score.
     *
     * @return List of highscores.
     */
    public ArrayList<HighScore> getHighScore() {
        Collections.sort(highScores, (hs1, hs2) -> {
            return Integer.parseInt(hs2.getScore()) - Integer.parseInt(hs1.getScore());
        });

        //highScores.sort(Comparator.comparing(HighScore::getDate).thenComparing(HighScore::getScore));
        if (highScores.size() > MAXLENGTH) {
            for (int i = 0; i < highScores.size(); i++) {
                highScores.subList(MAXLENGTH, highScores.size()).clear();
            }
        }

        return highScores;
    }

    /**
     * Add new highScore to highScoreList.
     *
     * @param hs A highscore.
     */
    public void addHighScore(HighScore hs) { highScores.add(hs); }

    /**
     * Remove highScore from highScoreList.
     *
     * @param hs A highscore.
     */
    public void removeHighScore(HighScore hs) {
        highScores.remove(hs);
    }
}