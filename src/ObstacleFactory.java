package src;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
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
        if (level == 0) {
            obstacle = makePowerUp();
        }else if (level == 1) {
            obstacle = makeAsteroid(1);
        } else if (level == 2) {
            obstacle = makeAsteroid(2);
        } else if (level == 3) {
            obstacle = makeAlien();
        }

        return obstacle;
    }

    public Asteroid makeAsteroid(int level) { //Add level system

     // Create a list to store lines
        List<Line> lines = new ArrayList<>();

        // Define the number of sides for the asteroid
        int sides = random.nextInt(5, 10); // Random number of sides between 5 and 9

        // Generate random points for the asteroid
        double[] xPoints = new double[sides];
        double[] yPoints = new double[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI / sides * i;
            double radius = random.nextDouble(10, 20);
            xPoints[i] = radius * Math.cos(angle);
            yPoints[i] = radius * Math.sin(angle);
        }

        // Create lines connecting these points
        for (int i = 0; i < sides; i++) {
            int nextIndex = (i + 1) % sides;
            lines.add(new Line(xPoints[i], yPoints[i], xPoints[nextIndex], yPoints[nextIndex]));
        }

        Color color = Color.WHITE;

        Asset asteroidAsset = new Asset(lines.toArray(new Line[0]), color);

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
        Asteroid newAsteroid;
        if (level == 1) {
            newAsteroid = new Asteroid(x,y, screenWidth, screenHeight, angle, spin, velocity, gameManager);
        } else {
            newAsteroid = new AsteroidWeird(x,y, screenWidth, screenHeight, angle, spin, velocity, gameManager);
            asteroidAsset.setColor(Color.WHEAT);
        }
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


    private GameObject makePowerUp() {
        Line[] lines = {
                new Line(10, 0, 4, 4),
                new Line(4, 4, 0, 10),
                new Line(0, 10, -4, 4),
                new Line(-4, 4, -10, 0),
                new Line(-10, 0, -4, -4),
                new Line(-4, -4, 0, -10),
                new Line(0, -10, 4, -4),
                new Line(4, -4, 10, 0)
        };

        Color color = Color.GOLD;

        Asset powerUpAsset = new Asset(lines, color);


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

        PowerUp powerUp = new PowerUp(x,y, screenWidth, screenHeight, angle, velocity, gameManager);
        powerUp.setAsset(powerUpAsset);

        return powerUp;

    }


}
