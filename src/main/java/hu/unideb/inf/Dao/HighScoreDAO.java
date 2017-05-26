package hu.unideb.inf.Dao;

/**
 * The {@code HighScoreDAO} is an Interface for DAO implementation.
 * @author MJ
 */
public interface HighScoreDAO {
    /**
     * Write a new {@link HighScore} to HighScore.xml.
     * @param highScore A highscore.
     */
    void addHighScore(HighScore highScore);

    /**
     * Read {@link HighScore} objects from HighScore.xml.
     * @return All the highscores.
     */
    HighScores getAllHighScores();
}