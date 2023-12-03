package src;

import javafx.scene.paint.Color;

public class ShipFactory {

    private double screenWidth;

    private double screenHeight;

    private GameManager gameManager;

    public ShipFactory(double screenWidth, double screenHeight, GameManager gameManager) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameManager = gameManager;
    }

    public Ship makeShip(int x, int y) {
        //Ship Shape
        Line[] lines = {
            new Line(-10, 10, 10, 10), // Base of the triangle
            new Line( 0, -10, -10, 10), // Left side
            new Line(10, 10, 0, -10)  // Right side
        };
        
        Color color = Color.WHITE;

        Asset shipAsset = new Asset(lines, color);

        Ship newShip = new Ship(x,y, screenWidth, screenHeight, gameManager);
        newShip.setAsset(shipAsset);



        return newShip;
    }
}
