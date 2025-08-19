package org.zvineug.physicsengine;

import java.awt.*;

public class Ball implements PhysicsObject2D{
    double radius;
    double mass;
    Vector2 velocity;
    Vector2 position;
    Color color;
    Vector2 postCollisionVelocity;
    boolean velocityWasUpdatedPostCollision;

    public Ball(double mass, double radius){
        this.mass = mass;
        this.radius = radius;
        this.velocity = Vector2.ZERO_VECTOR;
        this.position = Vector2.ZERO_VECTOR;
        this.color = Color.BLACK;
    }

    public Ball(double mass, double radius, Vector2 position){
        this(mass, radius);
        this.position = position;
    }

    public Ball(double mass, double radius, Vector2 position, Vector2 velocity){
        this(mass, radius, position);
        this.velocity = velocity;
    }

    public Ball(double mass, double radius, Vector2 position, Vector2 velocity, Color color){
        this(mass, radius, position, velocity);
        this.color = color;
    }

    public void collision(PhysicsObject2D other){
        double m1 = mass;
        double m2 = other.getMass();
        Vector2 distance = other.getPosition().add(this.position.scale(-1));
        Vector2 normalLine = distance.scale(1/distance.magnitude());
        Vector2 tangentLine = new Vector2(normalLine.y * -1, normalLine.x);

        double v1normal = this.velocity.dot(normalLine);
        double v1tangental = this.velocity.dot(tangentLine);
        double v2normal = other.getVelocity().dot(normalLine);

        double v1fnormal = (m1*v1normal + 2*(m2*v2normal) - m2*v1normal) / (m1 + m2);
        Vector2 vfVector = normalLine.scale(v1fnormal).add(tangentLine.scale(v1tangental));

        velocityWasUpdatedPostCollision = false;
        postCollisionVelocity = vfVector;
    }

    public void updateVelocityPostCollision(){
        if (!velocityWasUpdatedPostCollision) {
            this.velocity = postCollisionVelocity;
            velocityWasUpdatedPostCollision = true;
        }
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public Color getColor(){
        return color;
    }

    public double getRadius(){
        return radius;
    }

}
