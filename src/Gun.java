package src;

import java.util.ArrayList;

public class Gun { //TODO add shotgun ie multiple bullets at once

    private final GameManager gameManager;

    private final double speed = 6;


    public Gun (GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public void shoot(double x, double y, double angle, double screenWidth, double screenHeight, String identification) {
        gameManager.addObject(new Bullet(x, y, screenWidth, screenHeight, angle, speed,gameManager, identification));
    }

}
