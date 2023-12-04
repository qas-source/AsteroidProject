package src;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected final GameManager gameManager;
    private List<Vector> customVertices = null;

    private boolean shouldSplit = false;
    private Vector center;
    private double splitSpeed = 1.0;

    protected Asset asset;

    public GameObject(double x, double y, double screenWidth, double screenHeight, GameManager gameManager){
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameManager = gameManager;
    }

    public GameObject(List<Vector> vertices, double x, double y, double screenWidth, double screenHeight, GameManager gameManager) {
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameManager = gameManager;
        this.customVertices = new ArrayList<>(vertices); // Use the provided vertices
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
        asset.update();
        velX += accX;
        velY += accY;

        x += velX;
        y += velY;

        loopEdge();

        asset.setColor(Color.WHITE);
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

    @Override
  public Vector[] getVertecies() {
    if (customVertices != null) {
        // Use custom vertices for split objects
        return customVertices.toArray(new Vector[0]);
    } else {
        // Logic for default vertices as per the original object
        Vector[] vertices = asset.getVertices();
        Vector[] transformedVertices = new Vector[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            double angleRad = Math.toRadians(angle);
            double vertexX = vertices[i].x * Math.cos(angleRad) - vertices[i].y * Math.sin(angleRad);
            double vertexY = vertices[i].x * Math.sin(angleRad) + vertices[i].y * Math.cos(angleRad);
            transformedVertices[i] = new Vector(vertexX + x, vertexY + y); // Transformed vertices based on object position and rotation
        }

        return transformedVertices;
    }
}

@Override
public void collided(String identification) {
    if (this instanceof Asteroid || this instanceof Alien) {
        if (!identification.equals("Ship")) {
            initiateSplit();
        }
    }
}
//WORKING ON
private void splitObject() {
    Vector[] vertices = getVertecies();
    Vector[] newVertices = new Vector[vertices.length];

    for (int i = 0; i < vertices.length; i++) {
        Vector start = vertices[i];
        Vector end = vertices[(i + 1) % vertices.length];
        Vector midPoint = new Vector((start.x + end.x) / 2, (start.y + end.y) / 2);

        Vector splitVector = midPoint.sub(center).normalize().multiply(splitSpeed);
        newVertices[i] = start.add(splitVector);
        newVertices[(i + 1) % vertices.length] = end.add(splitVector);
    }

    customVertices = new ArrayList<>(Arrays.asList(newVertices));
}
    
protected void initiateSplit() {
    if (this instanceof Asteroid || this instanceof Alien) {
        asset.splitObject();
    }
}

    @Override
    public String getIdentification() {
        return "";
    }
}
