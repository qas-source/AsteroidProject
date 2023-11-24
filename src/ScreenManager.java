package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Manages Drawing to the canvas through the drawable interface
 */
public class ScreenManager {

    private GraphicsContext draw;
    private Canvas canvas;

    public ScreenManager(GraphicsContext draw, Canvas canvas) {
        this.draw = draw;
        this.canvas = canvas;
    }

// run drawable function
    public void draw(Drawable object) {

    }

//refresh
    public void clear() {
        draw.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
