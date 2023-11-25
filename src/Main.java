package src;

import javafx.animation.AnimationTimer;
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
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext draw = canvas.getGraphicsContext2D();
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, 500, 500);

        // create a Group
        Group group = new Group(canvas);


        // create a scene
        Scene scene = new Scene(group, 500, 500);

        // set the scene
        stage.setScene(scene);




        gameManager = new GameManager(draw, canvas);


        //final long startNanoTime = System.nanoTime(); // Use to get time since started

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gameManager.run();
            }
        }.start();


        stage.show();
    }


}
