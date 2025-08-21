package org.zvineug.physicsengine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.*;

public class PhysicsCanvas extends Canvas{

    Image bufferedImage;
    Graphics bufferedImageGraphics;

    ArrayList<Ball> physicsObjectsToDraw = new ArrayList<>();
    final int SCALE = 5;
    Ball selected;
    double screenSizeInMeters;

    int width;
    int height;

    public PhysicsCanvas(double screenSizeInMeters){

        this.setSize(750,750);

        width = this.getWidth();
        height = this.getHeight();

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
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if(bufferedImage == null){
            bufferedImage = createImage(width, height);
            bufferedImageGraphics = bufferedImage.getGraphics();
        }

        bufferedImageGraphics.clearRect(0,0, width, height);

        for(Ball ball : physicsObjectsToDraw){
            bufferedImageGraphics.setColor(ball.getColor());
            Vector2 pos = ball.getPosition();
            int xpos = (int) ((pos.x) * SCALE);
            int ypos = (int) ((pos.y) * SCALE);
            int rad = (int) ball.getRadius() * SCALE;
            xpos -= rad;
            ypos -= rad;
            bufferedImageGraphics.fillArc(xpos, ypos, rad*2, rad*2, 0, 360);
            if(ball == selected){
                bufferedImageGraphics.setColor(Color.RED);
                bufferedImageGraphics.drawArc(xpos, ypos, rad*2, rad*2, 0, 360);
                bufferedImageGraphics.drawString("Position: " + ball.getPosition(), 10,20);
                bufferedImageGraphics.drawString("Velocity: " + ball.getVelocity(), 10,40);
                bufferedImageGraphics.drawString("Radiuis: " + ball.getRadius(), 10,60);
                bufferedImageGraphics.drawString("Mass: " + ball.getMass(), 10,80);
            }
        }
        g.drawImage(bufferedImage, 0, 0, this);
    }

}
