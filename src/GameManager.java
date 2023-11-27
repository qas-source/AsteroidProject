package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GameManager {

    ScreenManager screenManager;

    ShipFactory shipFactory;

    ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas){
        shipFactory = new ShipFactory(canvas.getWidth(), canvas.getHeight());
        screenManager = new ScreenManager(draw, canvas);
        gameObjects.add(shipFactory.makeShip(200, 200));
    }

    public void run(){

        for (GameObject gameObject: gameObjects) {
            gameObject.update();
        }

        screenManager.clear();
        screenManager.draw(gameObjects);
    }

    public void controls(ArrayList<String> input){
        for (GameObject object: gameObjects) {
            object.controls(input);
        }
    }

}
