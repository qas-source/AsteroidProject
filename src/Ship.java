package src;

import java.util.ArrayList;

import javafx.scene.paint.Color;
/**
 * Player Ship class
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Ship extends GameObject{

    final private double acceletation = 0.1;
    final private double damping = 0.99;

     private double angularAcc = 0;
     private double angularVel = 0;
     private Gun gun;
     private boolean isSpacePressed = false;
     private int hitCount = 0;
     private final int maxHits = 3;
    private final Color hitColor = Color.RED;
    private final Color normalColor = Color.WHITE;
    private boolean isInvulnerable = false;
    private final long invulnerabilityDuration = 1000;
    private long lastHitTime = 0;


    public Ship(int x, int y, double screenWidth, double screenHeight, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        gun = new Gun(gameManager);
    }

    /**
     * Handles input controls of the ship
     * @param input, array of inputs
     */
    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;
        angularAcc = 0;



        if (input.contains("UP")){ // Sets respective accelerations
            setComponents(acceletation);
        }
        if (input.contains("LEFT")){
            angularAcc -= 0.5;
        } if (input.contains("RIGHT")){
            angularAcc += 0.5;
        }   if (input.contains("SPACE")) {
            if (!isSpacePressed) {
                gun.shoot(x, y, angle, screenWidth, screenHeight, "Bullet");
                isSpacePressed = true;
            }
        } else {
            isSpacePressed = false;
        }
    }


    /**
     * Handles the frame to frame logic
     */
    @Override
    public void update() { // Updates direction based on spin
        super.update();
        angularVel += angularAcc;
        angle += angularVel;
        damp();

        if (isInvulnerable && System.currentTimeMillis() - lastHitTime > invulnerabilityDuration) { // Checks if can be hit
            isInvulnerable = false;
            asset.setColor(normalColor);
        }
    }

    /**
     * Adds a friction force to movement
     */
    private void damp(){
        velX *= damping;
        velY *= damping;
        angularVel *= 0.9;
    }

    /**
     * Set acceleration components based on angle
     * @param value, acceleration scaler
     */
    private void setComponents(double value){
        accX += Math.sin(Math.toRadians(angle))*value;
        accY += -Math.cos(Math.toRadians(angle))*value;
    }

    /**
     * Returns the identification code for collision
     * @return an identification code
     */
    @Override
    public String getIdentification() {
        return "Ship";
    }

    /**
     * Is called on collision with any object
     * @param identification, get identification code of what it collided with
     */
    @Override
    public void collided(String identification) {

        if (identification.equals("PowerUp")){ // Resets health
            hitCount = 0;
            return;
        }

        if (identification.equals("Bullet") || isInvulnerable) { // Hit own bullet
            return;
        }

        hitCount++; // Decrease health
        lastHitTime = System.currentTimeMillis();
        isInvulnerable = true;
        flashRed();

        if (hitCount >= maxHits) {
            gameOver();
        }
    }

    /**
     * Flashes player red
     */
    private void flashRed() {
        asset.setColor(hitColor); // Flash red on hit
    }

    /**
     * Set gameOver on death
     */
    private void gameOver() {
        // Game over logic
        int currentScore = gameManager.getScoreManager().getScore();
        gameManager.getGameOverManager().showGameOverScreen(currentScore);
        gameManager.endGame();
    }
}
