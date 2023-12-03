package src;

import javafx.scene.paint.Color;

import java.util.Random;


public class ObstacleFactory {
    private double screenWidth;

    private double screenHeight;

    private Random random = new Random();

    private GameManager gameManager;

    public ObstacleFactory(double screenWidth, double screenHeight, GameManager gameManager) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameManager = gameManager;
    }

    public GameObject makeObstacle(int level) { //Add level system
        GameObject obstacle = null;

        if (level == 1) {
            obstacle = makeAsteroid();
        } else if (level == 2) {
            obstacle = makeAlien();
        }

        return obstacle;
    }

    private GameObject makeAsteroid(){
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

        double position = random.nextDouble(screenHeight + screenWidth); // First loops over the x's for the top, then down the side

        double x = position;
        if (x > screenWidth) {
            x = -20;
        }
        double y = position - screenWidth;
        if (y < 0) {
            y = -20;
        }
        Asteroid newAsteroid = new Asteroid(x,y, screenWidth, screenHeight, angle, spin, velocity, gameManager);
        newAsteroid.setAsset(asteroidAsset);

        return newAsteroid;
    }

    private GameObject makeAlien(){
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

        Color color = Color.GREEN;

        Asset alienAsset = new Asset(lines, color);

        double angle = random.nextDouble(360);

        double velocity = random.nextDouble(0.25, 3);

        double position = random.nextDouble(screenHeight + screenWidth); // First loops over the x's for the top, then down the side

        double x = position;
        if (x > screenWidth) {
            x = -20;
        }
        double y = position - screenWidth;
        if (y < 0) {
            y = -20;
        }
        Alien newAlien = new Alien(x,y, screenWidth, screenHeight, angle, velocity, gameManager);
        newAlien.setAsset(alienAsset);


        return newAlien;

    }

}
