package src;

import java.util.ArrayList;

public class GameObject implements Drawable, Collidable{


    protected double accX = 0;

    protected double accY = 0;

    protected double velX = 0;

    protected double velY = 0;


    protected double x = 0;

    protected double y = 0;

    protected double scale = 1;

    protected double angle = 90;

    protected Asset asset;
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getScale() {
        return scale;
    }

    public double getAngle() {
        return angle;
    }

    public Asset getAsset() {
        return asset;
    }

    public void update() {
        velX += accX;
        velY += accY;

        x += velX;
        y += velY;
    }

    public void controls(ArrayList<String> input){
        return;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
