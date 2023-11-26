package src;

import javafx.scene.paint.Color;

public class ShipFactory {


    public Ship makeShip(int x, int y) {
        //Ship Shape
        Line[] lines = {
            new Line(-10, 0, 10, 0), // Base of the triangle
            new Line(-10, 0, 0, -20), // Left side
            new Line(10, 0, 0, -20)  // Right side
        };
        
    Color color = Color.WHITE;

    Asset shipAsset = new Asset(lines, color);
    
    Ship newShip = new Ship(x,y);
    newShip.setAsset(shipAsset);  



    return newShip;
    }
}
