package src;

public class Asteroid extends GameObject{

    private final double spinVel;

    private final double velocity;
    public Asteroid(int x, int y, double screenWidth, double screenHeight, double angle, double spinVel, double velocity) {
        super(x, y, screenWidth, screenHeight);
        this.angle = angle;
        this.spinVel = spinVel;
        this.velocity = velocity;
        setVelovityComponents();
    }

    private void setVelovityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    @Override
    public void update() {
        super.update();
        angle += spinVel;
    }
}
