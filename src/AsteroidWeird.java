package src;

public class AsteroidWeird extends Asteroid{

    private double counter = 0;

    private double counterSpeed = 0.1;

    private double acceleration = 0.1;

    public AsteroidWeird(double x, double y, double screenWidth, double screenHeight, double angle, double spinVel, double velocity, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, angle, spinVel, velocity, gameManager);
    }

    @Override
    public void update() {
        counter += counterSpeed;

        accX = Math.sin(counter)*acceleration;
        accY = Math.cos(counter)*acceleration;

        super.update();
    }


}
