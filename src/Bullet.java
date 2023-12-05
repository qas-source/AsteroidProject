package src;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    private final double velocity;

    private final String identification;

    /**
     *
     * @param x, Starting x position
     * @param y, Starting y position
     * @param screenWidth, Width of screen, used for edgeLooping
     * @param screenHeight, Height of screen, used for edgeLooping
     * @param angle, starting direction of motion
     * @param velocity, starting speed of motion
     * @param gameManager, reference to the gameManager
     * @param identification, sets the identification code of the bullet
     */
    public Bullet(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager, String identification) {
        super(x, y, screenWidth, screenHeight, gameManager);

        this.identification = identification;

        this.angle = angle;
        this.velocity = velocity;

        Line[] lines = {
                new Line(0, -10, 0, 10) // Straight Line Shape
        };

        Color color = Color.WHITE;

        setAsset(new Asset(lines, color));

        setVelocityComponents();
    }

    /**
     * Set the initial velocity components
     */
    private void setVelocityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    /**
     * Runs the frame to frame logic
     */
    @Override
    public void update() {

        if (0 > x || x > screenWidth) { // If out of bounds, remove
            gameManager.removeObject(this);
        } if (0 > y || y > screenHeight) {
            gameManager.removeObject(this);
        }
        super.update();

    }

    /**
     * Returns the identification code for collision
     * @return an identification code
     */
    @Override
    public String getIdentification() {
        return identification;
    }
}
