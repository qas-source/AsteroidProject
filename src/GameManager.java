package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GameManager {

    private final ScreenManager screenManager;

    private ShipFactory shipFactory;
    private ObstacleFactory obstacleFactory;

    private final CollisionManager collisionManager = new CollisionManager();

    private final ArrayList<GameObject> gameObjects = new ArrayList<>();

    private ArrayList<Ship> players = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas){
        obstacleFactory = new ObstacleFactory(canvas.getWidth(), canvas.getHeight());
        shipFactory = new ShipFactory(canvas.getWidth(), canvas.getHeight());
        screenManager = new ScreenManager(draw, canvas);
        players.add(shipFactory.makeShip(200, 200));
        gameObjects.addAll(players);

        for (int i = 0; i < 1; i++) {
            gameObjects.add(obstacleFactory.makeObstacle(1));
        }

    }

    public void run(){

        for (GameObject gameObject: gameObjects) {
            gameObject.update();
        }

        collisionManager.collide(gameObjects);
        
        screenManager.clear();
        screenManager.draw(gameObjects);
    }

    public void controls(ArrayList<String> input){
        for (GameObject object: gameObjects) {
            object.controls(input);
        }
    }

    public ArrayList<Ship> getPlayers() {
        return players;
    }
}
