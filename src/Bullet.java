package src;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    private final double velocity;

    private final String identification;


    public Bullet(double x, double y, double screenWidth, double screenHeight, double angle, double velocity, GameManager gameManager, String identification) {
        super(x, y, screenWidth, screenHeight, gameManager);

        this.identification = identification;

        this.angle = angle;
        this.velocity = velocity;

        Line[] lines = {
                new Line(0, -10, 0, 10)//Single line for now TODO make it a circle or oval
        };

        Color color = Color.WHITE;

        setAsset(new Asset(lines, color));//TODO BETTER SHAPE

        setVelovityComponents();
    }

    private void setVelovityComponents(){
        velX = Math.sin(Math.toRadians(angle))*velocity;
        velY = -Math.cos(Math.toRadians(angle))*velocity;
    }

    @Override
    public void update() {




        if (0 > x || x > screenWidth) {
            gameManager.removeObject(this);
        } if (0 > y || y > screenHeight) {
            gameManager.removeObject(this);
        }
        super.update();

    }


    @Override
    public String getIdentification() {
        return identification;
    }
}
