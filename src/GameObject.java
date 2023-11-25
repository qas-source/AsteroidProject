package src;

public class GameObject implements Drawable, Collidable{




    int x = 0;

    int y = 0;

    int scale = 1;

    int angle = 0;

    Asset asset = new Asset();

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScale() {
        return scale;
    }

    public int getAngle() {
        return angle;
    }

    public Asset getAsset() {
        return asset;
    }


}
