package org.zvineug.physicsengine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Test{

    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < 5; i++){
            Vector2 ballPosition = new Vector2(Math.abs(r.nextDouble()) * 100, Math.abs(r.nextDouble()*100));
            Vector2 ballVelocity = new Vector2((int) (r.nextDouble()*100), (int) (r.nextDouble()*100));
            Ball ball = new Ball(1, 7, ballPosition, ballVelocity, Color.BLACK);
            balls.add(ball);
        }

//        Newton's Cradle
//        Ball ball1 = new Ball(1, 5, new Vector2(30, 50));
//        Ball ball2 = new Ball(1, 5, new Vector2(40, 50));
//        Ball ball3 = new Ball(1, 5, new Vector2(50, 50));
//        Ball ball4 = new Ball(1, 5, new Vector2(60, 50));
//        Ball ball5 = new Ball(1, 5, new Vector2(0, 50), new Vector2(30, 0));
//        balls.add(ball1);
//        balls.add(ball2);
//        balls.add(ball3);
//        balls.add(ball4);
//        balls.add(ball5);
        
        PhysicsCanvas canvas = new PhysicsCanvas(5);
        PhysicsManager pm = new PhysicsManager(canvas, 750, balls, true);
        pm.start();
    }
}
