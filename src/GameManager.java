package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import src.GameState;
import src.Difficulty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.input.KeyEvent;
public class GameManager {

    private GameState currentState = GameState.MENU; // Add this line
    private final ScreenManager screenManager;
    private ShipFactory shipFactory;
    private ObstacleManager obstacleManager;
    private final CollisionManager collisionManager = new CollisionManager();
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Ship> players = new ArrayList<>();
    private MenuManager menuManager;
    private Difficulty difficulty = Difficulty.EASY;
    private int obstacleSpawnTimer = 0;
    private ScoreManager scoreManager;
    private HighScoreManager highScoreManager;
    private GameOverManager gameOverManager;
    private List<GameObject> gameObjectsToAdd = new ArrayList<>();
    private List<GameObject> gameObjectsToRemove = new ArrayList<>();
    private boolean isGameOverScreenVisible = false;

    public GameManager(GraphicsContext draw, Canvas canvas, HighScoreManager highScoreManager) {
        this.currentState = GameState.MENU;
        this.scoreManager = new ScoreManager();
        this.highScoreManager = highScoreManager;
        this.menuManager = new MenuManager(draw, this, highScoreManager);
        this.gameOverManager = new GameOverManager(this, highScoreManager, scoreManager, draw);

        obstacleManager = new ObstacleManager(difficulty, canvas.getWidth(), canvas.getHeight(), this);

        shipFactory = new ShipFactory(canvas.getWidth(), canvas.getHeight(), this);
        screenManager = new ScreenManager(draw, canvas);
        players.add(shipFactory.makeShip(200, 200));
        gameObjects.addAll(players);

        gameObjects.addAll(obstacleManager.init());

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
    gameObjects.addAll(obstacleManager.update());

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
    public void controls(ArrayList<String> input){
        if (menuManager.getCurrentState() == GameState.MENU) {
            menuManager.handleInput(input);
        } else if (currentState == GameState.RUNNING) {
        for (GameObject object: gameObjects) {
            object.controls(input);
        }
    }
    }

    public void handleInput(KeyEvent keyEvent) {
        if (isGameOverScreenVisible) {
            // Reset the game and hide the game over screen
            resetGame();
            setGameOverScreenVisible(false);
        }
    }

    public void setGameOverScreenVisible(boolean isVisible) {
        this.isGameOverScreenVisible = isVisible;
    }
    public void resetGame() {
        // Logic to reset the game
        setCurrentState(GameState.MENU);
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
    }

    public GameOverManager getGameOverManager() {
        return gameOverManager;
    }

    public void displayGameOverScreen() {
        screenManager.displayGameOver(scoreManager.getScore());
    }


    public int getObjectCount() {
        return gameObjects.size();
    }
    public ScoreManager getScoreManager() {
        return this.scoreManager;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
}
