package hu.unideb.inf.Dao;

/**
 * Interface for DAO implementation.
 *
 * @author MJ
 *
 */
public interface HighScoreDAO {
    /**
     * Write a new HighScore to HighScore.xml.
     *
     * @param highScore
     */
    void addHighScore(HighScore highScore);

    /**
     * Read HighScores from HighScore.xml.
     *
     * @return All the highscores.
     */
    HighScores getAllHighScores();
}