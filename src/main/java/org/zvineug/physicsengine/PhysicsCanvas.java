package org.zvineug.physicsengine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.awt.*;

public class PhysicsCanvas extends Canvas{

    ArrayList<Ball> physicsObjectsToDraw = new ArrayList<>();
    final int SCALE = 5;
    Ball selected;
    double screenSizeInMeters;

    public PhysicsCanvas(double screenSizeInMeters){
        this.screenSizeInMeters = screenSizeInMeters;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vector2 pos = new Vector2(e.getX() / SCALE, e.getY() / SCALE);
                for(Ball ball : physicsObjectsToDraw){
                    if(Math.abs(pos.straightDistanceTo(ball.getPosition())) < ball.getRadius()){
                        selected = ball;
                        break;
                    }
                }
            }
        });

    }

    public void add(Ball physicsObject){
        physicsObjectsToDraw.add(physicsObject);
    }

    public void add(ArrayList<Ball> physicsObjects){
        physicsObjectsToDraw.addAll(physicsObjects);
    }

    private Vector2 worldToScreenSpace(Vector2 worldPosition){
        worldPosition.add(new Vector2(screenSizeInMeters,screenSizeInMeters));
        return Vector2.ZERO_VECTOR;
    }

    @Override
    public void paint(Graphics g) {
        for(Ball ball : physicsObjectsToDraw){
            g.setColor(ball.getColor());
            Vector2 pos = ball.getPosition();
            int xpos = (int) ((pos.x) * SCALE);
            int ypos = (int) ((pos.y) * SCALE);
            int rad = (int) ball.getRadius() * SCALE;
            xpos -= rad;
            ypos -= rad;
            g.fillArc(xpos, ypos, rad*2, rad*2, 0, 360);
            if(ball == selected){
                g.setColor(Color.RED);
                g.drawArc(xpos, ypos, rad*2, rad*2, 0, 360);
                g.drawString("Position: " + ball.getPosition(), 10,20);
                g.drawString("Velocity: " + ball.getVelocity(), 10,40);
                g.drawString("Radiuis: " + ball.getRadius(), 10,60);
                g.drawString("Mass: " + ball.getMass(), 10,80);
            }
        }
    }
}
