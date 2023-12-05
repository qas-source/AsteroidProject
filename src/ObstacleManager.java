package src;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleManager {

    private ObstacleFactory obstacleFactory;
    private Difficulty difficulty;
    private GameManager gameManager;

    private Random random = new Random();
    private int[][] difficultyPools = { //Defines obstacles based on difficulty.
            {1, 1, 1, 1, 1, 1, 1, 2, 3, 0},
            {1, 1, 1, 1, 1, 2, 2, 3, 3, 0},
            {1, 1, 1, 2, 2, 2, 3, 3, 3, 0},
    };
    //Defines growthrate depending on difficulty.
    private final int easyThreshold = 8;
    private final int mediumThreshold = 5;
    private final int hardThreshold = 3;
    private final int easyGrowth = 9;
    private final int mediumGrowth = 7;
    private final int hardGrowth = 6;

    private int obstacleCount = 0;
    // Constructor initializes obstacle manager.
    public ObstacleManager(Difficulty difficulty, double screenWidth, double screenHeight, GameManager gameManager) {
        this.difficulty = difficulty;
        this.gameManager = gameManager;
        obstacleFactory = new ObstacleFactory(screenWidth, screenHeight, gameManager);
    }
    // Initializes the game with a set of obstacles based on the difficulty.
    public ArrayList<GameObject> init() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
    // Adds initial set of obstacles to the game.
        for (int i = 0; i < 10; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(1));
        }
    //Adds different types of obstacles.
        for (int i = 0; i < 1; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(2));
        }

        obstacleCount = gameManager.getObjectCount() + gameObjects.size();

        return gameObjects;
    }

    public ArrayList<GameObject> update() { // Updates the obstacles in the game based on the current difficulty.
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        //New obstacles depending on the difficulty.
        switch (difficulty) {
            case EASY:
                if (obstacleCount - gameManager.getObjectCount() >= easyThreshold) {

                    int[] difficultyPool = difficultyPools[0];
                    for (int i = 0; i < easyGrowth; i++) {
                        gameObjects.add(obstacleFactory.makeObstacle(difficultyPool[random.nextInt(difficultyPool.length)]));
                    }
                }
            case MEDIUM:
                if (obstacleCount - gameManager.getObjectCount() >= mediumThreshold) {
                    int[] difficultyPool = difficultyPools[1];
                    for (int i = 0; i < mediumGrowth; i++) {
                        gameObjects.add(obstacleFactory.makeObstacle(difficultyPool[random.nextInt(difficultyPool.length)]));
                    }
                }
            case HARD:
                if (obstacleCount - gameManager.getObjectCount() >= hardThreshold) {
                    int[] difficultyPool = difficultyPools[2];
                    for (int i = 0; i < hardGrowth; i++) {
                        gameObjects.add(obstacleFactory.makeObstacle(difficultyPool[random.nextInt(difficultyPool.length)]));
                    }
                }
        }


        return gameObjects;
    }

}
