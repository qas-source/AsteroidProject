package src;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
/**
 * Main run class of the program, sets up the javaFx window
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Main extends Application {
    GameManager gameManager;

    /**
     * Main function of the game
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asteroids");
        Canvas canvas = new Canvas(1000, 700); // Creates canvas for game

        // You only need one GraphicsContext variable
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Fill the canvas background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Create a Group and add the canvas to it
        Group group = new Group(canvas);

        // Instantiate HighScoreManager only once
        HighScoreManager highScoreManager = new HighScoreManager();

        // Create GameManager with the GraphicsContext and HighScoreManager
        gameManager = new GameManager(gc, canvas, highScoreManager);

        // Create a scene and set it to the stage
        Scene scene = new Scene(group, canvas.getWidth(), canvas.getHeight());
        stage.setScene(scene);

        // Setup keyboard input handling
        ArrayList<String> input = new ArrayList<>();
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!input.contains(code))
                input.add(code);
        });
        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        // Start the game loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gameManager.run();
                gameManager.controls(input);
            }
        }.start();

        // Show the stage
        stage.show();
    }


}
