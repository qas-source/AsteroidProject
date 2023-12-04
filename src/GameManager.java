package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import src.GameState;
import src.Difficulty;
import java.util.ArrayList;
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

    public GameManager(GraphicsContext draw, Canvas canvas){
        this.currentState = GameState.MENU;
        this.menuManager = new MenuManager(draw, this);
        this.scoreManager = new ScoreManager();
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
    public void run(){
   
    if (menuManager.getCurrentState() == GameState.MENU) {
            menuManager.drawMenu();
        } else if (currentState == GameState.RUNNING) {
             for (GameObject gameObject: gameObjects) {
            gameObject.update();    
            updateObstacleSpawnTimer(); 
        }
        gameObjects.addAll(toAdd);
        gameObjects.removeAll(toDelete);
        toAdd.clear();
        toDelete.clear();

        collisionManager.collide(gameObjects);

        screenManager.clear();
        screenManager.draw(gameObjects);
        scoreManager.drawScore(screenManager.getGraphicsContext());
        }
    
      
    }

    public void incrementScore(int points) {
        scoreManager.addScore(points);
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

    public ArrayList<Ship> getPlayers() {
        return players;
    }

    public void  addObject(GameObject object) {
        toAdd.add(object);
    }

    public void  removeObject(GameObject object) {
        toDelete.add(object);
    }

    public void startGame() {
        currentState = GameState.RUNNING;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        System.out.println("Difficulty now set to: " + difficulty); 
    }

}
