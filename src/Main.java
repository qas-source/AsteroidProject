package src;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    GameManager gameManager;

    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Asteroids");
        Canvas canvas = new Canvas(100.0f, 100.0f);
        GraphicsContext draw = canvas.getGraphicsContext2D();
        draw.setFill(Color.BLACK);

        // create a Group
        Group group = new Group(canvas);


        // create a scene
        Scene scene = new Scene(group, 200, 200);

        // set the scene
        stage.setScene(scene);

        stage.show();


        gameManager = new GameManager(draw, canvas);
    }
}
