package src;

import javafx.scene.paint.Color;

/**
 * Class for asteroid obstacles
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Asteroid extends GameObject{

    private final double spinVel; // The spin on the asteroid

    private final double velocity;
    public Asteroid(double x, double y, double screenWidth, double screenHeight, double angle, double spinVel, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        this.angle = angle;
        this.spinVel = spinVel;
        this.velocity = velocity;
        setVelovityComponents();
    }

    /**
     * Set the initial velocity components
     */
    private void setVelovityComponents(){ //TODO make another asteroid that has sin or cos based acceleration
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    /**
     * Runs the frame to frame logic
     */
    @Override
    public void update() {
        super.update();
        angle += spinVel; // Spins the asteroid

    }
    /**
     * Returns the identification code for collision
     * @return an identification code
     */
    @Override
    public String getIdentification() {
        return "Asteroid";
    }

    /**
     * Is called on collision with any object
     * @param identification, get identification code of what it collided with
     */
    @Override
    public void collided(String identification) {
        if (identification.matches("Asteroid") ||identification.matches("Alien")){
            return;
        }else if (identification.matches("Ship")){
            asset.setColor(Color.RED);
            asset.splitObject();
        } else {
            asset.setColor(Color.BLUEVIOLET);
            gameManager.incrementScore(1);
            asset.splitObject();
        }
        
    }
}
