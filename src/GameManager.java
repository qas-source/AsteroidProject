package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import src.GameState;
import src.Difficulty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.input.KeyEvent;

/**
 * Manages the over all game logic
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class GameManager {

    private GameState currentState = GameState.MENU; // Current State of the game
    private final ScreenManager screenManager; // Renders the gameOjects
    private final ShipFactory shipFactory; // Used to generate ships
    private final ObstacleManager obstacleManager; // Used to generate obstacles
    private final CollisionManager collisionManager = new CollisionManager(); // Handles collisions
    private final ArrayList<GameObject> gameObjects = new ArrayList<>(); // Contains all objects on scree
    private ArrayList<Ship> players = new ArrayList<>(); // List of players
    private MenuManager menuManager;
    private Difficulty difficulty = Difficulty.EASY;
    private ScoreManager scoreManager;
    private GameOverManager gameOverManager;
    private List<GameObject> gameObjectsToAdd = new ArrayList<>();
    private List<GameObject> gameObjectsToRemove = new ArrayList<>();
    private boolean isGameOverScreenVisible = false;
    private Runnable resetAction; // Reset Action
    /**
     * Constructor for the gamaManager
     * @param draw, graphics context of the canvas
     * @param canvas, canvas that is used as a screen
     * @param highScoreManager, the highs-core manager
     */
    public GameManager(GraphicsContext draw, Canvas canvas, HighScoreManager highScoreManager, Runnable resetAction){
        this.resetAction = resetAction;
        // Starts in menu

        this.scoreManager = new ScoreManager(); // Sets up managers and factories
        this.menuManager = new MenuManager(draw, this, highScoreManager);
        this.gameOverManager = new GameOverManager(this, highScoreManager, scoreManager, draw);

        obstacleManager = new ObstacleManager(difficulty, canvas.getWidth(), canvas.getHeight(), this);

        shipFactory = new ShipFactory(canvas.getWidth(), canvas.getHeight(), this);
        screenManager = new ScreenManager(draw, canvas);
        players.add(shipFactory.makeShip(200, 200));
        gameObjects.addAll(players);

        gameObjects.addAll(obstacleManager.init()); // Initialises the game

    }

    /**
     * The frame to frame loop of the games, calls various update functions
     */
    public void run() {
        // Handle menu drawing
        if (menuManager.getCurrentState() == GameState.MENU) {
            menuManager.drawMenu();
            return; // Skip game logic if in menu
        }

        if (gameOverManager.isGameOver()) {
            return;
        }

        // Update game objects and check collisions
        for (GameObject gameObject : new ArrayList<>(gameObjects)) {
            gameObject.update();
        }



        collisionManager.collide(new ArrayList<>(gameObjects));
        gameObjects.addAll(obstacleManager.update());

        // Process objects to add or remove after updates and collision checks
        processGameObjects();

        if (gameOverManager.isGameOver()) { // Checks if game is over after collisions to prevent from screen clear
            return;
        }

        // Update the screen
        screenManager.clear();
        screenManager.draw(gameObjects);
        scoreManager.drawScore(screenManager.getGraphicsContext());
    }

    /**
     * Increments the score
     * @param points, point value
     */
    public void incrementScore(int points) {
        scoreManager.addScore(points);
    }


        /**
         * Calls the input function for all gameObjects
         * @param input, array of inputs form main
         */
    public void controls(ArrayList<String> input){
        if (menuManager.getCurrentState() == GameState.MENU) {
            menuManager.handleInput(input);
        } else if (currentState == GameState.RUNNING) {
        for (GameObject object: gameObjects) {
            object.controls(input);
        }
        if (isGameOverScreenVisible) {
                // Reset the game and hide the game over screen
                resetGame();
                setGameOverScreenVisible(false);
            }
    }
    }


    public void setGameOverScreenVisible(boolean isVisible) {
        this.isGameOverScreenVisible = isVisible;
    }
    public void resetGame() {
        // Logic to reset the game
        if (resetAction != null) {
            resetAction.run();
        } else {
            System.out.println("Failed Reset");
        }
      /*  setCurrentState(GameState.MENU);
        gameObjects.clear();
        players.clear(); */
        // Additional reset logic as needed
    }

    /**
     * Sets current game state
     * @param state, new game state
     */
    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    /**
     * Gets list of players
     * @return the list of players
     */
    public ArrayList<Ship> getPlayers() {
        return players;
    }

    /**
     * Handles request for adding and removing gameObjects
     */
    private void processGameObjects() {
        gameObjects.addAll(gameObjectsToAdd);
        gameObjectsToAdd.clear();
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();
    }

    /**
     * Request for an object to be added
     * @param object, object to be added to gameObjects
     */
    public void  addObject(GameObject object) {
        gameObjectsToAdd.add(object);
    }

    /**
     * Request for an object to be removed
     * @param object, object to be removed from gameObjects
     */
    public void  removeObject(GameObject object) {
        gameObjectsToRemove.add(object);
    }

    /**
     * Starts the game
     */
    public void startGame() {
        currentState = GameState.RUNNING;
    }

    /**
     * Set the current diffuculty
     * @param difficulty, new difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the game over manager
     * @return the gameOverManager
     */
    public GameOverManager getGameOverManager() {
        return gameOverManager;
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
