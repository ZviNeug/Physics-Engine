package org.zvineug.physicsengine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Test{

    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < 20; i++){
            Vector2 ballPosition = new Vector2(Math.abs(r.nextDouble()) * 200, Math.abs(r.nextDouble()*200));
            Vector2 ballVelocity = new Vector2((int) (r.nextDouble()*100), (int) (r.nextDouble()*100));
            Ball ball = new Ball(1, 5, ballPosition, ballVelocity, Color.BLACK);
            balls.add(ball);
        }
        PhysicsCanvas canvas = new PhysicsCanvas(5);
        PhysicsManager pm = new PhysicsManager(canvas, 750, balls, true);
        pm.start();
    }
}
