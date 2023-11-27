package src;

import java.util.ArrayList;

public class GameObject implements Drawable, Collidable{

    protected final double screenWidth;

    protected final double screenHeight;

    private final double edgeGap = 30.0;
    protected double accX = 0;

    protected double accY = 0;

    protected double velX = 0;

    protected double velY = 0;


    protected double x = 0;

    protected double y = 0;

    protected double scale = 1;

    protected double angle = 90;

    protected Asset asset;
    public GameObject(int x, int y, double screenWidth, double screenHeight){
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
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

        loopEdge();
    }

    private void loopEdge() {
        x = (x + screenWidth + 3 * edgeGap) % (screenWidth + 2 * edgeGap) - edgeGap;
        y = (y + screenHeight + 3 * edgeGap) % (screenHeight + 2 * edgeGap) - edgeGap;
    }

    public void controls(ArrayList<String> input){
        return;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
