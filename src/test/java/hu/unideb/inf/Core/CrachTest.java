package hu.unideb.inf.Core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static hu.unideb.inf.Core.Main.failGame;
import static hu.unideb.inf.Core.Main.winHeight;
import static java.lang.Math.random;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CrachTest {
    private Ship ship;
    private static final ArrayList<Wall> walls = new ArrayList<>();

    @Before
    public void setShip(){
        ship = new Ship();
    }

    @Before
    public void setWalls(){
        for (int i = 0; i < 100; i++) {
            int enter = (int)(random()*100+100);
            int height = new Random().nextInt(winHeight-enter);
            Wall wall = new Wall(height, 1);
            wall.setTranslateX(i*350+winHeight);
            wall.setTranslateY(0);
            walls.add(wall);
        }
    }

    @Test
    public void testShip(){
        assertNotNull(ship);
    }

    @Test
    public void testWalls(){
        assertNotNull(walls);
    }

    @Test
    public void crachTest(){
        ship.jump();
        while (!failGame) {
            if (ship.velocity.getY() < 5) {
                ship.velocity = ship.velocity.add(0, 1);
            }
            ship.moveX((int) ship.velocity.getX());
            ship.moveY((int) ship.velocity.getY());
        }
        assertTrue(failGame);
    }
}
