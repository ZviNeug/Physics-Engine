package org.zvineug.physicsengine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

public class BallTest {

    Ball ball1;
    Ball ball2;

    @BeforeEach
    public void setup(){
        Vector2 ball1Velocity = new Vector2(3, -2);
        Vector2 ball1Position = new Vector2(-0.4, 0);
        ball1 = new Ball(0.5, 0.2, ball1Position, ball1Velocity);
        ball2 = new Ball(1, 0.2);
    }

    @Test
    public void testCollision(){
        ball1.collision(ball2);
        Vector2 expectedVelocity = new Vector2(-1,-2);
        ball1.updateVelocityPostCollision();
        assertEquals(expectedVelocity, ball1.getVelocity());
    }

    @Test
    public void deleteme(){
        ball1 = new Ball(3,1, new Vector2(Math.sqrt(3)/2,0.5), new Vector2(4,1));
        ball2 = new Ball(2,1, Vector2.ZERO_VECTOR, new Vector2(0,3));
        ball1.collision(ball2);
        ball2.collision(ball1);
        ball1.updateVelocityPostCollision();
        ball2.updateVelocityPostCollision();
//        ball1.velocity.print();
//        ball2.velocity.print();
        Ball ball3 = new Ball(1,5, new Vector2(50,50), Vector2.ZERO_VECTOR, Color.BLACK);
        Ball ball4 = new Ball(1,5, new Vector2(42,56), new Vector2(50,0), Color.BLACK);
        ball3.collision(ball4);
        ball4.collision(ball3);
        ball3.updateVelocityPostCollision();
        ball4.updateVelocityPostCollision();
        ball3.velocity.print();
        ball4.velocity.print();
    }

}
