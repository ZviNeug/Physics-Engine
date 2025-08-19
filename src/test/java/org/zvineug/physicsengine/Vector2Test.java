package org.zvineug.physicsengine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class Vector2Test {

    Vector2 v1;
    Vector2 v2;
    Vector2 v3;

    @BeforeEach
    public void setup(){
        v1 = new Vector2(2.5, 3);
        v2 = new Vector2(5, 6.25);
        v3 = new Vector2(2.5, 3);
    }

    @Test
    public void testDot(){
        assertEquals(31.25, v1.dot(v2));
    }

    @Test
    public void testUnitVector(){
        Vector2 expected = new Vector2(0.640184, 0.768221);
        Vector2 retrived = v1.unitVector();
        double percision = 1000000;
        retrived.x = (double) ((int)(retrived.x * percision)) / percision;
        retrived.y = (double) ((int)(retrived.y * percision)) / percision;
        assertEquals(expected, retrived);
    }

    @Test
    public void testScale(){
        Vector2 retrievedVector = v1.scale(3);
        assertEquals(7.5, retrievedVector.x);
        assertEquals(9, retrievedVector.y);
    }

    @Test
    public void testAdd(){
        Vector2 retrievedVector = v1.add(v2);
        assertEquals(7.5, retrievedVector.x);
        assertEquals(9.25, retrievedVector.y);
    }

    @Test
    public void testMagnitude(){
        double retrivedMagnitude = v1.magnitude();
        double percision = 1000000;
        retrivedMagnitude = (double) ((int)(retrivedMagnitude * percision)) / percision;
        assertEquals(3.905124, retrivedMagnitude);
    }

    @Test
    public void testStraightDistanceTo(){
        double retrivedDistance = v1.straightDistanceTo(v2);
        double percision = 1000000;
        retrivedDistance = (double) ((int)(retrivedDistance * percision)) / percision;
        assertEquals(4.100304, retrivedDistance);
    }

    @Test
    public void testDistanceVector(){
        Vector2 expectedDistanceVector = new Vector2(2.5, 3.25);
        assertEquals(expectedDistanceVector, v1.distanceVector(v2));
    }

    @Test
    public void testDirectionTowards(){
        Vector2 expected = new Vector2(0.609710, 0.792623);
        Vector2 retrived = v1.directionTowards(v2);
        double percision = 1000000;
        retrived.x = (double) ((int)(retrived.x * percision)) / percision;
        retrived.y = (double) ((int)(retrived.y * percision)) / percision;
        assertEquals(expected, retrived);
    }

    @Test
    public void testEquals(){
        assertTrue(v1.equals(v3));
        assertFalse(v1.equals(v2));
    }
}
