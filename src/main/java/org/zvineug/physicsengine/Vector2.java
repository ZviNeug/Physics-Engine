package org.zvineug.physicsengine;

public class Vector2 {
    public double x;
    public double y;

    static Vector2 ZERO_VECTOR = new Vector2(0,0);

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double dot(Vector2 other){
        return this.x * other.x + this.y * other.y;
    }

    public Vector2 unitVector(){
        double angle = Math.atan2(this.y, this.x);
        return new Vector2(Math.cos(angle), Math.sin(angle));
    }

    public Vector2 scale(double scalar){
        return new Vector2(this.x * scalar, this.y * scalar);
    }
    public Vector2 add(Vector2 other){
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public double straightDistanceTo(Vector2 other){
        return this.distanceVector(other).magnitude();
    }

    public Vector2 distanceVector(Vector2 other){
        double xDistance = Math.abs(this.x - other.x);
        double yDistance = Math.abs(this.y - other.y);
        return new Vector2(xDistance, yDistance);
    }

    public Vector2 directionTowards(Vector2 other){
        return this.distanceVector(other).unitVector();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Vector2){
            Vector2 otherVector = (Vector2) other;
            return this.x == otherVector.x && this.y == otherVector.y;
        }
        return false;
    }

    @Override
    public String toString(){
        return "x: " + this.x + " , y: " + this.y;
    }

    // Debug method
    public void print(){
        System.out.println("x: " + this.x + " , y: " + this.y);
    }

}
