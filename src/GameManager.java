package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameManager {

    ScreenManager screenManager;

    ShipFactory shipFactory = new ShipFactory();

    ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas){
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
