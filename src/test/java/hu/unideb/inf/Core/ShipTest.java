package hu.unideb.inf.Core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
    private Ship ship;

    @Before
    public void setShip(){
        ship = new Ship();
    }

    @Test
    public void testShip(){
        assertNotNull(ship);
    }

    @Test
    public void testmoveX(){
        ship.moveX(20);
        assertEquals(120.0,ship.getTranslateX(),1);
    }
    @Test
    public void testmoveY(){
        ship.moveY(20);
        assertEquals(320.0,ship.getTranslateY(),1);
    }

    @Test
    public void testjump(){
        ship.jump();
        assertEquals(3,ship.velocity.getX(),1);
        assertEquals(-15,ship.velocity.getY(),1);
    }

    @Test
    public void testshipNull(){
        ship.shipNull();
        assertEquals(100,ship.getTranslateX(),1);
        assertEquals(300,ship.getTranslateY(),1);
        assertEquals(0,ship.velocity.getX(),1);
        assertEquals(0,ship.velocity.getY(),1);
    }
}
