package src;

public class PowerUp extends GameObject{

    private final double velocity;

    public PowerUp(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        this.angle = angle;
        this.velocity = velocity;
        setVelovityComponents();
    }

    private void setVelovityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    @Override
    public String getIdentification() {
        return "PowerUp";
    }

    @Override
    public void collided(String identification) {
        if (identification.matches("Ship")) {
            asset.splitObject();
        }
    }

}
