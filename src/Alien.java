package src;

import javafx.scene.paint.Color;

public class Alien extends GameObject {

    private final int cooldown = 120;
    private int counter = 0;

    final private double damping = 0.8;

    final private double acceletation = 0.5;


    private Gun gun;

    private final double velocity;
    public Alien(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        this.angle = angle;
        this.velocity = velocity;
        setVelovityComponents();
        gun = new Gun(gameManager);
    }

    private void setVelovityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    private void shoot(){
        counter ++;
        counter = counter % cooldown;
        if (counter != 0) {
            return;
        }

        counter ++;
        counter = counter % cooldown;

        Ship player = gameManager.getPlayers().get(0); // TODO make 2 player friendly(choose random index)
        double angleBetween = Math.toDegrees(Math.atan2(player.x - x, y - player.y));
        gun.shoot(x, y, angleBetween, screenWidth, screenHeight, "Alien");
    }

    private void damp(){
        velX *= damping;
        velY *= damping;
    }
    @Override
    public void update() {
        Ship player = gameManager.getPlayers().get(0); // TODO make 2 player friendly(choose random index)
        accX = ( player.x - x + 30 ) / screenWidth * acceletation;
        accY = ( player.y - y + 40) / screenHeight * acceletation;
        if (asset.isSplittingComplete()) {
            gameManager.removeObject(this); // Remove the alien after splitting
        }

        if (accX > 2) {
            accX = 2;
        } if (accY > 2) {
            accY = 2;
        }

        if (velX > 10) {
            velX = 10;
        } if (velY > 10) {
            velY = 10;
        }

        damp();

        super.update();

        shoot();

        asset.setColor(Color.GREEN);
    }

    @Override
    public String getIdentification() {
        return "Alien";
    }

    @Override
    public void collided(String identification) {
        if (identification.matches("Ship") || identification.matches("Asteroid") || identification.matches("Alien")) {
            asset.setColor(Color.DARKGREEN);
        } else {
            asset.setColor(Color.CYAN);
            asset.splitObject(); 
            gameManager.incrementScore(1);
        }
    }


}
