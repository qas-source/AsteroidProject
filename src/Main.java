package src;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Main run class of the program, sets up the JavaFX window.
 * This class is responsible for initializing the game and handling the main game loop.
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Main extends Application {
    private GameManager gameManager;
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext gc;
    private Scene scene;
    private HighScoreManager highScoreManager;
    private ArrayList<String> input;

    /**
     * Main function of the game.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Asteroids");
        setupGame();

        // Start the main game loop using an AnimationTimer.
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gameManager.run();
                gameManager.controls(input);
            }
        }.start();

        // Show the stage to make the window visible.
        stage.show();
    }


    public void setupGame() {
        // Initialize the input list for handling keyboard inputs.
        input = new ArrayList<>();
        canvas = new Canvas(1000, 700);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Group group = new Group(canvas);
        scene = new Scene(group, canvas.getWidth(), canvas.getHeight());

        // Set up the keyboard input event handlers.
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!input.contains(code))
                input.add(code);
        });
        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        // Initialize HighScoreManager and GameManager, and attach them to the scene.
        highScoreManager = HighScoreManager.getInstance();
        gameManager = new GameManager(gc, canvas, highScoreManager, this::setupGame);

        // Apply the scene to the stage.
        stage.setScene(scene);
    }
}
