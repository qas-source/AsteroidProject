package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import src.GameState;
import src.Difficulty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    private GameState currentState = GameState.MENU; // Add this line
    private final ScreenManager screenManager;
    private ShipFactory shipFactory;
    private ObstacleFactory obstacleFactory;
    private final CollisionManager collisionManager = new CollisionManager();
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Ship> players = new ArrayList<>();
    private ArrayList<GameObject> toDelete = new ArrayList<>();
    private ArrayList<GameObject> toAdd = new ArrayList<>();
    private MenuManager menuManager;
    private Difficulty difficulty = Difficulty.EASY;
    private int obstacleSpawnTimer = 0;
    private ScoreManager scoreManager;
    private HighScoreManager highScoreManager;
    private GameOverManager gameOverManager;
    private List<GameObject> gameObjectsToAdd = new ArrayList<>();
    private List<GameObject> gameObjectsToRemove = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas, HighScoreManager highScoreManager){
        this.currentState = GameState.MENU;
        this.scoreManager = new ScoreManager();
        this.highScoreManager = highScoreManager;
        this.menuManager = new MenuManager(draw, this, highScoreManager);
        this.gameOverManager = new GameOverManager(this, highScoreManager, scoreManager);
        obstacleFactory = new ObstacleFactory(canvas.getWidth(), canvas.getHeight(), this);
        shipFactory = new ShipFactory(canvas.getWidth(), canvas.getHeight(), this);
        screenManager = new ScreenManager(draw, canvas);
        players.add(shipFactory.makeShip(200, 200));
        gameObjects.addAll(players);

        for (int i = 0; i < 10; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(1));
        }

        for (int i = 0; i < 1; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(2));
        }

    }
//Editing
public void run() {
    // Handle menu drawing
    if (menuManager.getCurrentState() == GameState.MENU) {
        menuManager.drawMenu();
        return; // Skip game logic if in menu
    }

    // Update game objects and check collisions
    for (GameObject gameObject : new ArrayList<>(gameObjects)) {
        gameObject.update();
    }
    collisionManager.collide(new ArrayList<>(gameObjects));

    // Process objects to add or remove after updates and collision checks
    processGameObjects();

    // Update the screen
    screenManager.clear();
    screenManager.draw(gameObjects);
    scoreManager.drawScore(screenManager.getGraphicsContext());
}

    public void incrementScore(int points) {
        scoreManager.addScore(points);
    }

    public void checkAndUpdateHighScore() {
        int currentScore = scoreManager.getScore();
        if (currentScore > highScoreManager.getHighScore()) {
            highScoreManager.setHighScore(currentScore);
        }
    }

    private void updateObstacleSpawnTimer() {
        obstacleSpawnTimer++;
        int spawnThreshold;

        switch (difficulty) {
            case EASY:
            System.out.println("Spawning in EASY mode");
                spawnThreshold = 1000000000; 
                break;
            case MEDIUM:
            System.out.println("Spawning in Medium mode");
                spawnThreshold = 100000000; // Normal spawn rate
                break;
            case HARD:
                spawnThreshold = 10000000; // Spawn more frequently
                break;
            default:
                spawnThreshold = 200;
        }

        if (obstacleSpawnTimer >= spawnThreshold) {
            spawnObstacle();
            obstacleSpawnTimer = 0; // Reset the timer
        }
    }

        private void spawnObstacle() {
        int level = new Random().nextInt(1, 3); // Randomly choose between 1 and 2
        GameObject newObstacle = obstacleFactory.makeObstacle(level);
        gameObjects.add(newObstacle);
    }
//
    public void controls(ArrayList<String> input){
        if (menuManager.getCurrentState() == GameState.MENU) {
            menuManager.handleInput(input);
        } else if (currentState == GameState.RUNNING) {
        for (GameObject object: gameObjects) {
            object.controls(input);
        }
    }
    }

    
    public void resetGame() {
        // Logic to reset the game
        gameObjects.clear();
        players.clear();
        // Additional reset logic as needed
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    public ArrayList<Ship> getPlayers() {
        return players;
    }

    private void processGameObjects() {
        gameObjects.addAll(gameObjectsToAdd);
        gameObjectsToAdd.clear();
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();
    }

    public void  addObject(GameObject object) {
        gameObjectsToAdd.add(object);
    }

    public void  removeObject(GameObject object) {
        gameObjectsToRemove.add(object);
    }

    public void startGame() {
        currentState = GameState.RUNNING;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        System.out.println("Difficulty now set to: " + difficulty); 
    }

    public GameOverManager getGameOverManager() {
        return gameOverManager;
    }
    
    public void displayGameOverScreen() {
        screenManager.displayGameOver(scoreManager.getScore());
    }
}
