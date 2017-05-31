package hu.unideb.inf.Dao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class HighScoresTest {
    private HighScores highScores;

    @Before
    public void setHighScores(){
        highScores = new HighScores();
    }

    @Test
    public void getHighScoreTest(){
        ArrayList<HighScore> highScoresList  = new ArrayList<HighScore>();
        assertArrayEquals(highScoresList.toArray(),highScores.getHighScore().toArray());
    }

    @Test
    public void setHighScoreTest(){
        ArrayList<HighScore> highScoresList  = new ArrayList<HighScore>();
        highScoresList.add(new HighScore("Player1","10","2017-01-01"));
        highScoresList.add(new HighScore("Player2","20","2017-02-02"));
        highScoresList.add(new HighScore("Player3","30","2017-03-03"));
        highScoresList.add(new HighScore("Player4","40","2017-04-04"));
        highScoresList.add(new HighScore("Player5","50","2017-05-05"));
        highScoresList.add(new HighScore("Player6","60","2017-06-06"));
        highScores.setHighScore(highScoresList);

        Collections.sort(highScoresList, (hs1, hs2) -> {
            return Integer.parseInt(hs2.getScore()) - Integer.parseInt(hs1.getScore());
        });

        if (highScoresList.size() > 5){
            for (int i = 0; i < highScoresList.size(); i++) {
                highScoresList.subList(5, highScoresList.size()).clear();
            }
        }

        assertArrayEquals(highScoresList.toArray(),highScores.getHighScore().toArray());
    }


    @Test
    public void addHighScoreTest(){
        HighScore highScore = new HighScore("Player","10","1999-01-11");
        highScores.addHighScore(highScore);

        ArrayList<HighScore> highScoresList  = new ArrayList<HighScore>();
        highScoresList.add(highScore);

        assertArrayEquals(highScoresList.toArray(),highScores.getHighScore().toArray());
    }
}
