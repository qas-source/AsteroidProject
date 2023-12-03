package src;

import javafx.scene.paint.Color;

import java.util.Random;


public class ObstacleFactory {
    private double screenWidth;

    private double screenHeight;

    private Random random = new Random();

    public ObstacleFactory(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Asteroid makeObstacle(int level) { //Add level system
        //Asteroid Shape TODO improve shape
        Line[] lines = {
                new Line(20, 0, 14, 14),
                new Line(14, 14, 0, 20),
                new Line(0, 20, -14, 14),
                new Line(-14, 14, -20, 0),
                new Line(-20, 0, -14, -14),
                new Line(-14, -14, 0, -20),
                new Line(0, -20, 14, -14),
                new Line(14, -14, 20, 0)
        };

        Color color = Color.WHITE;

        Asset asteroidAsset = new Asset(lines, color);

        double angle = random.nextDouble(360);

        double spin = random.nextDouble(0.5, 4);

        double velocity = random.nextDouble(0.25, 3);

        int position = random.nextInt((int) (screenHeight + screenWidth)); // First loops over the x's for the top, then down the side

        int x = position;
        if (x > screenWidth) {
            x = -20;
        }
        int y = (int) (position - screenWidth);
        if (y < 0) {
            y = -20;
        }
        Asteroid newAsteroid = new Asteroid(x,y, screenWidth, screenHeight, angle, spin, velocity);
        newAsteroid.setAsset(asteroidAsset);

        return newAsteroid;
    }



}
