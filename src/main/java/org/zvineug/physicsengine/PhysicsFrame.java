package org.zvineug.physicsengine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PhysicsFrame extends Frame {

    boolean simulationIsOn = false;

    public PhysicsFrame(){
        super("Physics Engine");
        this.setSize(750, 750);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                PhysicsFrame.super.dispose();
            }
        });

        Button playPauseButton = new Button("Play");
    }
}
