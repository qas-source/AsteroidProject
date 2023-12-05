package src;

import javafx.scene.paint.Color;

/**
 * Class that controls the speacific logic involved with the Alien enemie
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */

public class Alien extends GameObject {

    private final int cooldown = 120; // Cool-down time between shoots
    private int counter = 0;

    final private double damping = 0.8; // Damping force on movement

    final private double acceletation = 0.5; // Speed of acceleration


    private final Gun gun; // Handles creating bullets

    private final double velocity; // Initial Speed of Alien

    /**
     *
     * @param x, Starting x position
     * @param y, Starting y position
     * @param screenWidth, Width of screen, used for edgeLooping
     * @param screenHeight, Height of screen, used for edgeLooping
     * @param angle, starting direction of motion
     * @param velocity, starting speed of motion
     * @param gameManager, reference to the gameManager
     */
    public Alien(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);

        this.angle = angle;
        this.velocity = velocity;
        gun = new Gun(gameManager);

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
     * Attempts to shoot a player, using the counter to limit the number of shots
     */
    private void shoot(){
        counter ++;
        counter = counter % cooldown;
        if (counter != 0) {
            return;
        }


        Ship player = gameManager.getPlayers().get(0); // TODO make 2 player friendly(choose random index)
        double angleBetween = Math.toDegrees(Math.atan2(player.x - x, y - player.y)); //Gets the shooting angle
        gun.shoot(x, y, angleBetween, screenWidth, screenHeight, "Alien"); //Creates the bullet
    }

    /**
     * Adds a friction force to movement
     */
    private void damp(){
        velX *= damping;
        velY *= damping;
    }

    /**
     * Runs the frame to frame logic
     */
    @Override
    public void update() {
        Ship player = gameManager.getPlayers().get(0); // TODO make 2 player friendly(choose random index)
        accX = ( player.x - x + 30 ) / screenWidth * acceletation; // Sets the accelerations
        accY = ( player.y - y + 40) / screenHeight * acceletation;


        if (accX > 2) { // Limits the max acceleration
            accX = 2;
        } if (accY > 2) {
            accY = 2;
        }

        if (velX > 10) { // Limits the max velocity
            velX = 10;
        } if (velY > 10) {
            velY = 10;
        }

        damp(); // Force of friction

        super.update(); // Updates position and other values

        shoot(); // Attempts to shoot the gun

        asset.setColor(Color.GREEN); // Resets the colour
    }

    /**
     * Returns the aliens identification code for collision
     * @return an identification code
     */
    @Override
    public String getIdentification() {
        return "Alien";
    }

    /**
     * Is called on collision with any object
     * @param identification, get identification code of what it collided with
     */
    @Override
    public void collided(String identification) {
        if (identification.matches("Ship") || identification.matches("Asteroid") || identification.matches("Alien")) {
            asset.setColor(Color.DARKGREEN);
        } else {
            asset.setColor(Color.CYAN);
            asset.splitObject();  // Destroys the alien
            gameManager.incrementScore(1);
        }
    }


}
