package src;

/**
 * Class for extended asteroid obstacles with strange movement
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class AsteroidWeird extends Asteroid{

    private double counter = 0; // Used to calculate the acceleration

    private double counterSpeed = 0.1;

    private double acceleration = 0.1;

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
    public AsteroidWeird(double x, double y, double screenWidth, double screenHeight, double angle, double spinVel, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, angle, spinVel, velocity, gameManager);
    }

    /**
     * Handles the frame to frame logic
     */
    @Override
    public void update() {
        counter += counterSpeed;

        accX = Math.sin(counter)*acceleration; // Sets the acceleration to cause odd movement
        accY = Math.cos(counter)*acceleration;

        super.update();
    }


}
