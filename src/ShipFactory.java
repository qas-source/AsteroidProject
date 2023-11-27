package src;

import javafx.scene.paint.Color;

public class ShipFactory {

    private double screenWidth;

    private double screenHeight;

    public ShipFactory(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Ship makeShip(int x, int y) {
        //Ship Shape
        Line[] lines = {
            new Line(-10, 10, 10, 10), // Base of the triangle
            new Line(-10, 10, 0, -10), // Left side
            new Line(10, 10, 0, -10)  // Right side
        };
        
    Color color = Color.WHITE;

    Asset shipAsset = new Asset(lines, color);
    
    Ship newShip = new Ship(x,y, screenWidth, screenHeight);
    newShip.setAsset(shipAsset);  



    return newShip;
    }
}
