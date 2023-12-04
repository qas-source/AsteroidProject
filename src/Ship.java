package src;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Ship extends GameObject{

    final private double acceletation = 0.1;
    final private double damping = 0.99;

     private double angularAcc = 0;
     private double angularVel = 0;

     private boolean previousShooting = false;
     private Gun gun;
     private boolean isSpacePressed = false;
     private int hitCount = 0;
     private final int maxHits = 3;
    private final Color hitColor = Color.RED;
    private final Color normalColor = Color.WHITE;
    private boolean isInvulnerable = false;
    private final long invulnerabilityDuration = 2000; 
    private long lastHitTime = 0;


    public Ship(int x, int y, double screenWidth, double screenHeight, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        gun = new Gun(gameManager);
    }

    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;
        angularAcc = 0;



        if (input.contains("UP")){
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
    

    @Override
    public void update() {
        super.update();
        angularVel += angularAcc;

        angle += angularVel;
        damp();
        if (isInvulnerable && System.currentTimeMillis() - lastHitTime > invulnerabilityDuration) {
            isInvulnerable = false;
            asset.setColor(normalColor); // Reset color after invulnerability
        }
    }

    private void damp(){
        velX *= damping;
        velY *= damping;
        angularVel *= 0.9;
    }

    private void setComponents(double value){
        accX += Math.sin(Math.toRadians(angle))*value;
        accY += -Math.cos(Math.toRadians(angle))*value;
    }

    @Override
    public String getIdentification() {
        return "Ship";
    }

    @Override
    public void collided(String identification) {
        if (identification.equals("Bullet") || isInvulnerable) {
            return; // Ignore collisions if invulnerable or hit by Bullet
        }

        hitCount++;
        lastHitTime = System.currentTimeMillis();
        isInvulnerable = true;
        flashRed();

        if (hitCount >= maxHits) {
            gameManager.getGameOverManager().endGame();
        }
    }

    private void flashRed() {
        asset.setColor(hitColor); // Flash red on hit
    }

    private void gameOver() {
        // Game over logic
        gameManager.getGameOverManager().endGame();
    }
}
