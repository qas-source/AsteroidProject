package src;

import javafx.scene.paint.Color;

public class Asteroid extends GameObject{

    private final double spinVel;

    private final double velocity;
    public Asteroid(double x, double y, double screenWidth, double screenHeight, double angle, double spinVel, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        this.angle = angle;
        this.spinVel = spinVel;
        this.velocity = velocity;
        setVelovityComponents();
    }

    private void setVelovityComponents(){ //TODO make another asteroid that has sin or cos based acceleration
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    @Override
    public void update() {
        super.update();
        angle += spinVel;
        if (asset.isSplittingComplete()) {
            gameManager.removeObject(this); // Remove the asteroid after splitting
        }
    }

    @Override
    public String getIdentification() {
        return "Asteroid";
    }

    @Override
    public void collided(String indentification) {
        if (indentification.matches("Asteroid") ||indentification.matches("Alien")){
            return;
        }else if (indentification.matches("Ship")){
            asset.setColor(Color.RED);
            asset.splitObject();
        } else {
            asset.setColor(Color.BLUEVIOLET);
            gameManager.incrementScore(1);
            asset.splitObject();
        }
        
    }
}
