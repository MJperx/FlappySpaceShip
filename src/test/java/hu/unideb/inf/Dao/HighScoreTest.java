package hu.unideb.inf.Dao;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class HighScoreTest {
    private HighScore highScore;
    private String date = String.valueOf(LocalDate.now());


    @Before
    public void setHighScore(){
        highScore = new HighScore("Player","10", date);
    }

    @Test
    public void testHighscore(){
        assertNotNull(highScore);
    }

    @Test
    public void getNameTest(){
        assertEquals("Player",highScore.getName());
    }

    @Test
    public void getScoreTest(){
        assertEquals("10",highScore.getScore());
    }

    @Test
    public void getDateTest(){
        assertEquals(date,highScore.getDate());
    }

    @Test
    public void setNameTest(){
        highScore.setName("Player");
        assertEquals("Player", highScore.getName());
    }

    @Test
    public void setScoreTest(){
        highScore.setScore("20");
        assertEquals("20", highScore.getScore());
    }

    @Test
    public void setDateTest(){
        LocalDate date = LocalDate.of(2013,01,01);
        highScore.setDate(String.valueOf(date));
        assertEquals(String.valueOf(date), highScore.getDate());
    }
}
