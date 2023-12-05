package src;

/**
 * PowerUp class
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class PowerUp extends GameObject{

    private final double velocity;

    public PowerUp(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        this.angle = angle;
        this.velocity = velocity;
        setVelovityComponents();
    }
    /**
     * Set velocity components based on angle
     */
    private void setVelovityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    /**
     * Returns the identification code for collision
     * @return an identification code
     */
    @Override
    public String getIdentification() {
        return "PowerUp";
    }

    /**
     * Is called on collision with any object
     * @param identification, get identification code of what it collided with
     */
    @Override
    public void collided(String identification) {
        if (identification.matches("Ship")) {
            asset.splitObject();
        }
    }

}
