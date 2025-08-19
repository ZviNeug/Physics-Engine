package org.zvineug.physicsengine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class PhysicsManager {

    Frame frame;
    long lastUpdatedTime;
    ArrayList<Ball> physicsObjects;
    PhysicsCanvas canvas;
    boolean frameIsActive;
    boolean collideWithSides;
    int FRAME_SIZE;

    public PhysicsManager(PhysicsCanvas canvas, int frameSize, ArrayList<Ball> physicsObjects, boolean collideWithSides){
        this.physicsObjects = physicsObjects;
        this.canvas = canvas;
        this.collideWithSides = collideWithSides;
        this.FRAME_SIZE = frameSize;

        frame = new Frame("Physics Engine");
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                frameIsActive = false;
            }
        });
        this.canvas.add(physicsObjects);
        frame.add(canvas);
    }

    public void start(){
        frame.setVisible(true);
        frameIsActive = true;
        canvas.repaint();
        lastUpdatedTime = System.nanoTime();

        while(frameIsActive){
            double deltatime = (double)(System.nanoTime() - lastUpdatedTime) / 1000000000.0;
            if(deltatime > 0.01) {
                for (Ball ball : this.physicsObjects) {
                    ball.position = ball.position.add(ball.velocity.scale(deltatime));
                }
                findCollisions();
                this.canvas.repaint();
                lastUpdatedTime = System.nanoTime();
            }
        }
    }

    private void findCollisions(){
        for(int i = 0; i<= physicsObjects.size(); i++){
            for(int j = i + 1; j < physicsObjects.size(); j++){
                Ball ball1 = physicsObjects.get(i);
                Ball ball2 = physicsObjects.get(j);
                double distance = Math.abs(ball1.getPosition().straightDistanceTo(ball2.getPosition()));
                double collisionDistance = ball1.getRadius() + ball2.getRadius();

                if(distance < collisionDistance){

                    double v1 = ball1.getVelocity().magnitude();
                    double v2 = ball2.getVelocity().magnitude();
                    double extraTime = (collisionDistance - distance) / Math.abs((v1 + v2));
                    ball1.position = ball1.position.add(ball1.velocity.scale(-1 * extraTime));
                    ball2.position = ball2.position.add(ball2.velocity.scale(-1 * extraTime));

                    ball1.collision(ball2);
                    ball2.collision(ball1);
                    ball1.updateVelocityPostCollision();
                    ball2.updateVelocityPostCollision();
                }
            }
        }
        if(collideWithSides){
            for(Ball ball : physicsObjects){
                Vector2 position = ball.getPosition();
                if((position.x-ball.getRadius())*5 <= 0){
                    ball.velocity.x = Math.abs(ball.velocity.x);
                }
                if((position.x+ball.getRadius()*2)*5 >= FRAME_SIZE){
                    ball.velocity.x = Math.abs(ball.velocity.x) * -1;
                }
                if((position.y-ball.getRadius())*5 <= 0){
                    ball.velocity.y = Math.abs(ball.velocity.y);
                }
                if((position.y+ball.getRadius()*2)*5 >= FRAME_SIZE){
                    ball.velocity.y = Math.abs(ball.velocity.y) * -1;
                }
            }
        }
    }

    private void logObjects(Ball obj1, Ball obj2){
        System.out.println("distance");
        System.out.println(obj1.getPosition().straightDistanceTo(obj2.getPosition()));
        System.out.println("obj1 vel: ");
        obj1.getVelocity().print();
        System.out.println("obj2 pos: ");
        obj2.getPosition().print();
        System.out.println("obj2 vel: ");
        obj2.getVelocity().print();
    }

}
