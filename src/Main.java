package src;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Asteroids");
        Canvas canvas = new Canvas(100.0f, 100.0f);
        GraphicsContext draw = canvas.getGraphicsContext2D();
        draw.setFill(Color.BLACK);

    }
}
