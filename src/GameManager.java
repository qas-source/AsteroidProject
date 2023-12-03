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

    private ArrayList<GameObject> toDelete = new ArrayList<>();
    private ArrayList<GameObject> toAdd = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas){
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

    public void run(){

        for (GameObject gameObject: gameObjects) {
            gameObject.update();
        }
        gameObjects.addAll(toAdd);
        gameObjects.removeAll(toDelete);
        toAdd.clear();
        toDelete.clear();

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

    public void  addObject(GameObject object) {
        toAdd.add(object);
    }

    public void  removeObject(GameObject object) {
        toDelete.add(object);
    }

}
