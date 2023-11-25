package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


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
        Asset asset = object.getAsset();

        // Put in actual code that uses asset
        // This is sorta template stuff
        draw.setFill(Color.WHITE);
        draw.fillRect(object.getX() - 10, object.getY() - 10, 20, 20);


    }
    
    public void draw(ArrayList<GameObject> objects){
        for (Drawable object: objects) {
            draw(object);
        }
    }

//refresh
    public void clear() {
        draw.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
