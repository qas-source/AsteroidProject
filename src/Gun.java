package src;

/**
 * Generate Bullets
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Gun { //TODO add shotgun ie multiple bullets at once

    private final GameManager gameManager;

    private final double speed = 6;


    public Gun (GameManager gameManager) {
        this.gameManager = gameManager;
    }


    /**
     * Creates a bullet and adds it to gameObjects
     * @param x, Starting x position
     * @param y, Starting y position
     * @param angle, starting direction of motion
     * @param screenWidth, Width of screen, used for edgeLooping
     * @param screenHeight, Height of screen, used for edgeLooping
     * @param identification, sets the identification code of the bullet
     */
    public void shoot(double x, double y, double angle, double screenWidth, double screenHeight, String identification) {
        gameManager.addObject(new Bullet(x, y, screenWidth, screenHeight, angle, speed,gameManager, identification));
    }

}
