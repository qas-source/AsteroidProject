package src;

import javafx.scene.canvas.GraphicsContext;

/**
 * Manages Drawing to the canvas through the drawable interface
 */
public class ScreenManager {

    private GraphicsContext pen;

    public ScreenManager(GraphicsContext pen) {
        this.pen = pen;
    }
}
