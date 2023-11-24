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

    ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameManager(GraphicsContext draw, Canvas canvas){
        screenManager = new ScreenManager(draw, canvas);
    }

    public void run(){
        // Draw code
    }

}
