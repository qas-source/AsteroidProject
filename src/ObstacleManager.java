package src;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleManager {

    private ObstacleFactory obstacleFactory;
    private Difficulty difficulty;
    private GameManager gameManager;

    private Random random = new Random();
    private int[][] difficultyPools = {
            {1, 1, 1, 1, 1, 1, 1, 2, 3},
            {1, 1, 1, 1, 1, 2, 2, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
    };

    private final int easyThreshold = 8;
    private final int mediumThreshold = 5;
    private final int hardThreshold = 3;
    private final int easyGrowth = 9;
    private final int mediumGrowth = 7;
    private final int hardGrowth = 6;

    private int obstacleCount = 0;

    public ObstacleManager(Difficulty difficulty, double screenWidth, double screenHeight, GameManager gameManager) {
        this.difficulty = difficulty;
        this.gameManager = gameManager;
        obstacleFactory = new ObstacleFactory(screenWidth, screenHeight, gameManager);
    }

    public ArrayList<GameObject> init() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(1));
        }

        for (int i = 0; i < 1; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(2));
        }

        obstacleCount = gameManager.getObjectCount() + gameObjects.size();

        return gameObjects;
    }

    public ArrayList<GameObject> update() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();


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
