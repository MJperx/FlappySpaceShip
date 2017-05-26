package hu.unideb.inf.Core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {
    private Wall wall;

    @Before
    public void setWall(){
        wall = new Wall(10,0);
    }

    @Test
    public void testWall(){
        assertNotNull(wall);
        assertEquals(10,wall.getWallHeight());
        assertEquals(0,wall.getOrientation());
    }


}
